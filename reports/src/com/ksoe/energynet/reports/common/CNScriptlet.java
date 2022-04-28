package com.ksoe.energynet.reports.common;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.fill.JRFillParameter;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.reports.Passport.TpInfoAddition4.powerRezervDS;
import com.ksoe.energynet.valueobject.CNSubsystemType;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.security.UserProfile;
import com.ksoe.netobjects.logic.PriconnectionDataLogic;
import com.ksoe.report.exception.ReportSystemException;

public class CNScriptlet extends JRDefaultScriptlet {

    private transient java.sql.Connection cnConnection = null;
    private transient java.sql.Connection netConnection = null;

    protected java.sql.Connection getConnection(String dataSourceName)
            throws DatasourceConnectException {
        try {
            if (cnConnection != null && !cnConnection.isClosed()) {
                return cnConnection;
            }

            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext
                    .lookup(dataSourceName);
            cnConnection = dataSource.getConnection();
            return cnConnection;
        } catch (NamingException e) {
            throw new DatasourceConnectException(dataSourceName, e);
        } catch (SQLException e) {
            throw new DatasourceConnectException(dataSourceName, e);
        }
    }

    @Override
	public void beforeReportInit() throws JRScriptletException {
        try {
            cnConnection = getConnection(AuthorizationJNDINames.CN_DATASOURCE);
        } catch (DatasourceConnectException e) {
            e.printStackTrace();
        }
    }

    protected java.sql.Connection getNetConnection(String dataSourceName)
            throws DatasourceConnectException {
        try {
            if (netConnection != null && !netConnection.isClosed()) {
                return netConnection;
            }

            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext
                    .lookup(dataSourceName);
            netConnection = dataSource.getConnection();
            return netConnection;
        } catch (NamingException e) {
            throw new DatasourceConnectException(dataSourceName, e);
        } catch (SQLException e) {
            throw new DatasourceConnectException(dataSourceName, e);
        }
    }

    /* $F{s04code}.intValue(), $P{gauge_date}, $F{s04nominalpower}, $F{gauge_power}  */

    public JRDataSource getConnectedPower(
            int tpCode, Date gauge_date,
            BigDecimal s04nominalpower, BigDecimal gauge_power) {
        try {

            ArrayList rows  = new ArrayList();
            String query = " /* Присоединяемые мощности */ " +

                    /* Агрегирование запроса для случая пустой выборки
                    * и расчёт резерва мощности только как суммы входящих параметров -
                    * номинальной и замерянной мощностей */

                    " select " +
                    "  cast(" + tpCode + " as double precision) as code_substation04, " +
                    "  sum(connections_power.pvtu) as pvtu, " +
                    "  case " +
                    "    when sum(connections_power.prezerv) is not Null " +
                    "      then sum(connections_power.prezerv) " +
                    "    else " +
                    "      coalesce(0.85 * coalesce(" + s04nominalpower + ", 0) - " +
                    "      coalesce(" + gauge_power + ", 0)) " +
                    "  end as prezerv, " +
                    "  string_agg(connections_power.packs_id::text, '. ') as packs_id " +

                    " from " +
                    " ( " +

                    /* Выборка суммы присоединяемых мощностей к
                    * ТП 10 - 6 / 0,4 кВ и расчёт резерва мощности как
                    * суммы входящих параметров - номинальной и замерянной мощностей,
                    * за вычетом суммы присоединяемых мощностей */

                    " select " +
                    " s04.code_substation04, " +
                    " coalesce(sum(s04.power_will_connect),0) as pvtu, " +

                    /**
                    *   Prezerv(кВт) = Pnom(кВт) - [Pisn(кВт) + Pvtu(кВт)]
                    *   Pnom(кВт) = [0.85 * s04nominalpower(кВА)](кВт)
                    *   Pisn(кВт) = gauge_power(кВт)
                    */

                    " coalesce(0.85 * coalesce(" + s04nominalpower + ",0) - " +
                    "     (coalesce(" + gauge_power + ",0) + coalesce(sum(s04.power_will_connect),0)),0) as prezerv, " +
                    " string_agg(s04.packs_id::text, '; ') as packs_id" +

                    " from " +
                    "   (select " +
                    "     L1.code_substation04, " +
                    "     sum(coalesce(p1.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.cn_techterms where id_pack = p1.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t1.pow_exist, 0) from cn.cn_techterms t1 " +
                    "                where t1.id_pack = p1.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ: ' || string_agg(p1.id::text, ', ')) as packs_id " +
                    "   from cn.cn_enlines L1, cn.cn_packages p1 " +
                    "   where L1.code_substation04 is not null " +
                    "    and (p1.id_pack_status in (1, 2, 5) " +
                    "      or (p1.id_pack_status in (3, 4) and coalesce(p1.is_reserv, 0) = 1) " +
                    "         and p1.id not in (select id_pack from cn.cn_movement " +
                    "         where id_movement_status = 1 and id_state in (1148, 1001000, 1001421, 1001223, " +
                    "           100062, 100012, 1000156, 1169, 1000022, 1152, 1000813, 100022, 1172, 1000817)))" +

                    /*
                    "     and L1.id_pack in (select distinct(id_pack) from cn.cn_movement where is_completed = 1 " +
                    "     and id_state in (1000158, 2000041)) " +
                    */

                    "   and ((L1.id_pack not in (select id_pack from cn.cn_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p1.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p1.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p1.id = L1.id_pack " +
                    "   and L1.code_substation04 = " + tpCode +
                    "   group by L1.code_substation04 " +

                    "   union all " +

                    "   select " +
                    "     L13.code_substation04, " +
                    "     sum(coalesce(p13.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.ncn_techterms where id_pack = p13.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t13.pow_exist, 0) from cn.ncn_techterms t13 " +
                    "                where t13.id_pack = p13.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ с 01.08.2010: ' || string_agg(p13.id::text, ', ')) as packs_id " +
                    "   from cn.ncn_enlines L13, cn.ncn_packages p13 " +
                    "   where L13.code_substation04 is not null " +
                    "    and (p13.id_pack_status in (1, 2, 5) " +
                    "      or (p13.id_pack_status in (3, 4) and coalesce(p13.is_reserv, 0) = 1) " +
                    "         and p13.id not in (select id_pack from cn.ncn_movement " +
                    "         where id_movement_status = 1 and id_state in (1001000, 1001223, 1000022, 1000817, " +
                    "           1000813, 1001421, 1000156, 1000)))" +

                    /*
                    "   and L13.id_pack in (select distinct(id_pack) from cn.ncn_movement where is_completed = 1 " +
                    "     and id_state in (1000158, 2000041)) " +
                    */

                    "   and ((L13.id_pack not in (select id_pack from cn.ncn_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p13.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_NEWCONNECTION +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p13.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_NEWCONNECTION +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p13.id = L13.id_pack " +
                    "   and L13.code_substation04 = " + tpCode +
                    "   group by L13.code_substation04 " +

                    "   union all " +

                    "   select " +
                    "     L18.code_substation04, " +
                    "     sum(coalesce(p18.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.cn_20110314_techterms where id_pack = p18.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t18.pow_exist, 0) from cn.cn_20110314_techterms t18 " +
                    "                where t18.id_pack = p18.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ с 14.03.2011: ' || string_agg(p18.id::text, ', ')) as packs_id " +
                    "   from cn.cn_20110314_enlines L18, cn.cn_20110314_packages p18 " +
                    "   where L18.code_substation04 is not null " +
                    "    and (p18.id_pack_status in (1, 2, 5) " +
                    "      or (p18.id_pack_status in (3, 4) and coalesce(p18.is_reserv, 0) = 1) " +
                    "         and p18.id not in (select id_pack from cn.cn_20110314_movement " +
                    "         where id_movement_status = 1 and id_state in (1000156, 1001223, 1001421, 1001000, 1000)))" +

                    "   and (L18.id_pack in (select distinct(id_pack) from cn.cn_20110314_movement where is_completed = 1 " +
                    "       and id_state in (1000150, 1000158, 2000034, 2000041)) " +
                    "     or L18.id_pack in (select id from cn.cn_20110314_packages " +
                    "       where coalesce(reg_num_tu_contract, '') <> ''))" +

                    "   and ((L18.id_pack not in (select id_pack from cn.cn_20110314_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p18.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION_20110314 +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p18.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION_20110314 +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p18.id = L18.id_pack " +
                    "   and L18.code_substation04 = " + tpCode +
                    "   group by L18.code_substation04 " +

                    "   union all " +

                    "   select " +
                    "     L20.code_substation04, " +
                    "     sum(coalesce(p20.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.eap_techterms where id_pack = p20.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t20.pow_exist, 0) from cn.eap_techterms t20 " +
                    "                where t20.id_pack = p20.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ с 01.03.2013: ' || string_agg(p20.id::text, ', ')) as packs_id " +
                    "   from cn.eap_enlines L20, cn.eap_packages p20 " +
                    "   where L20.code_substation04 is not null " +
                    "    and (p20.id_pack_status in (1, 2, 5) " +
                    "      or (p20.id_pack_status in (3, 4) and coalesce(p20.is_reserv, 0) = 1) " +
                    "         and p20.id not in (select id_pack from cn.eap_movement " +
                    "         where id_movement_status = 1 and id_state in (1000)))" +

                    "   and (L20.id_pack in (select distinct(id_pack) from cn.eap_movement where is_completed = 1 " +
                    "       and id_state in (1050028, 1050032, 1070026, 1070030, 2050011, 2050014)) " +
                    "     or L20.id_pack in (select id from cn.eap_packages " +
                    "       where coalesce(reg_num_tu_contract, '') <> ''))" +

                    "   and ((L20.id_pack not in (select id_pack from cn.eap_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p20.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p20.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p20.id = L20.id_pack " +
                    "   and L20.code_substation04 = " + tpCode +
                    "   group by L20.code_substation04 " +

                    " ) s04 " +

                    " group by s04.code_substation04 " +

                    /* Исключено избыточное упорядочивание по коду подстанции -
                    * в подчинённом запросе максимум только одна строка: */
                    //" order by s04.code_substation04 " +

                    " ) connections_power";

            if (cnConnection == null || cnConnection.isClosed()){
                cnConnection = getConnection(AuthorizationJNDINames.CN_DATASOURCE);
            }

            Statement tempSt;
            tempSt = cnConnection.createStatement();
            ResultSet rs = tempSt.executeQuery(query);

            while(rs.next()) {
                {
                    HashMap hashMap = new HashMap();
                    hashMap.put(powerRezervDS.pVTU, rs.getBigDecimal(2));
                    hashMap.put(powerRezervDS.pREZERV, rs.getBigDecimal(3));
                    hashMap.put(powerRezervDS.packs_id, rs.getString(4));
                    rows.add(hashMap);
                }
            }

            rs.close();
            tempSt.close();

            return new powerRezervDS(rows.toArray());

        } catch (SQLException e) {
            throw new ReportSystemException(e);
        } catch (DatasourceConnectException e) {
            throw new ReportSystemException(e);
        } finally {
            try {
                if (cnConnection != null && !cnConnection.isClosed()) {
                    cnConnection.close();
                }
            } catch (SQLException e) {
            }
        }
    }


    /* $F{s04code}.intValue(), $P{gauge_date}, $F{maxadmispower}, $F{gauge_power}, $F{s04typecode}  */

    public JRDataSource getConnectedPowerByMaxAdmis(
            int tpCode, Date gauge_date,
            BigDecimal maxadmispower, BigDecimal gauge_power, Double s04typecode, BigDecimal tr_cnt) {
        try {

            ArrayList rows  = new ArrayList();

            String query = "";

            //http://10.77.11.28:8080/browse/NET-4352
            //По причине представления пользователями строки таблицы высоковольтных подстанций
            //не как понижающей станции в целом, а как отдельного трансформатора этой станции,
            //появились дублированные записи номенклатурной таблицы net.substation150
            //подсистемы ОБЪЕКТОВ ЭНЕРГЕТИКИ комплекса EnergyNet.
            //Поэтому появилась таблица net.ad2substation, связывающая высоковольтные подстанции
            //из подаваемой диспетчерами таблицы загруженности высоковольтных станций net.ad4subst150
            //с высоковольтными станциями справочной таблицы net.substation150,
            //что влияет на публикуемый отчёт о Резервах мощностей.
            //В связи с этим на уровне сервера приложений проекта EnergyNet
            //в данном файле CNScriptlet.java изменились функции
            //getConnectedAndReservPowerByResolution115, getConnectedPowerByMaxAdmis
            String substCode = "";
            if (s04typecode.doubleValue() == 1 || s04typecode.doubleValue() == 2)
            {
	            String selectStr = "select net.group_concat(cast(a2s.substationcode as varchar), ', ') " +
	              " from net.ad2substation a2s where a2s.adcode in ( " +
	              "   select ad2st.adcode from net.ad2substation ad2st " +
	              "   where ad2st.substationcode = " + tpCode + ")";
	            PreparedStatement statement = netConnection.prepareStatement(selectStr);
	            ResultSet set = statement.executeQuery();
	            if (set.next()) {
		          substCode = set.getString(1);}
	            set.close();
	            statement.close();
            }

            if (s04typecode.doubleValue() == 1)
            {
                query = " /* Присоединяемые мощности */ " +
                /* Агрегирование запроса для случая пустой выборки
                * и расчёт резерва мощности только как суммы входящих параметров -
                * номинальной и замерянной мощностей */

                " select " +
                "  cast(" + tpCode + " as double precision) as code_ss150, " +
                "  sum(connections_power.pvtu) as pvtu, " +
                "  case " +
                "    when sum(connections_power.prezerv) is not Null " +
                "      then sum(connections_power.prezerv) " +
                "    else " +
                "      coalesce(coalesce(" + maxadmispower + ", 0) - " +
                "      coalesce(" + gauge_power + ", 0)) " +
                "  end as prezerv, " +
                "  string_agg(connections_power.packs_id::text, '. ') as packs_id, " +

                "  case " +
                "    when sum(connections_power.prezerv) is not Null " +
                "      then sum(connections_power.prezerv) * 250 " +
                "    else " +
                "      coalesce(coalesce(" + maxadmispower + ", 0) - " +
                "      coalesce(" + gauge_power + ", 0)) * 250 " +
                "  end as prezervcost " +

                " from " +
                " ( " +

                /* Выборка суммы присоединяемых мощностей к
                * ПС 150 / 35 / 10 - 6 кВ и расчёт резерва мощности как
                * суммы входящих параметров - номинальной и замерянной мощностей,
                * за вычетом суммы присоединяемых мощностей */



                " select " +
                " s150.code_ss150, " +
                " (coalesce(sum(s150.power_will_connect),0) / coalesce(" + tr_cnt + ", 1)) as pvtu, " +

                /**
                *   Prezerv(кВт) = Pnom(кВт) - [Pisn(кВт) + Pvtu(кВт)]
                *   Pnom(кВт) = [0.85 * s150nominalpower(кВА)](кВт)
                *   Pisn(кВт) = gauge_power(кВт)
                */

                " coalesce(coalesce(" + maxadmispower + ",0) - " +
                "     (coalesce(" + gauge_power + ",0) + coalesce(sum(s150.power_will_connect),0) / coalesce(" + tr_cnt + ", 1)),0) as prezerv, " +
                " string_agg(s150.packs_id::text, '; ') as packs_id" +

                " from " +
                "   (select " +
                "     L1.code_ss150, " +
                "     sum(coalesce(p1.power, 0) - " +
                "        case " +
                "          when exists(select id from cn.cn_techterms where id_pack = p1.id and id_proposal not in (2, 3)) " +
                "            then " +
                "              (select coalesce(t1.pow_exist, 0) from cn.cn_techterms t1 " +
                "                where t1.id_pack = p1.id) " +
                "            else 0 " +
                "        end " +
                "      ) as power_will_connect, " +
                "     ('ПРИСОЕДИНЕНИЕ: ' || string_agg(p1.id::text, ', ')) as packs_id " +
                "   from cn.cn_enlines L1, cn.cn_packages p1 " +
                "   where L1.code_ss150 is not null " +
                "    and (p1.id_pack_status in (1, 2, 5) " +
                "      or (p1.id_pack_status in (3, 4) and coalesce(p1.is_reserv, 0) = 1) " +
                "         and p1.id not in (select id_pack from cn.cn_movement " +
                "         where id_movement_status = 1 and id_state in (1148, 1001000, 1001421, 1001223, " +
                "           100062, 100012, 1000156, 1169, 1000022, 1152, 1000813, 100022, 1172, 1000817)))" +

                /*
                "     and L1.id_pack in (select distinct(id_pack) from cn.cn_movement where is_completed = 1 " +
                "     and id_state in (1000158, 2000041)) " +
                */

                "   and ((L1.id_pack not in (select id_pack from cn.cn_techterms " +
                "          where coalesce(is_realized, -1) = 1) " +
                "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                "            where spl2cn.id_cn_pack = p1.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION +
                "            and spl2cn.id_spl_pack in (select splp.id " +
                "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                "              and splp.id = spl2cn.id_spl_pack " +
                "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                "                and splm.id_state in (select id " +
                "                  from cn.spl_states where id_state_status = 2) " +
                //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                " ))) " +
                "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                "            where pp2cn.id_cn_pack = p1.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION +
                "            and pp2cn.id_pp_pack in (select ppp.id " +
                "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                "              and ppp.id = pp2cn.id_pp_pack " +
                "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                "                and ppm.id_state in (select id " +
                "                  from cn.pp_states where id_state_status = 2) " +
                //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                " ))))) " +

                "   and p1.id = L1.id_pack " +
                "   and L1.code_ss150 in (" + substCode + ")" + //NET-4352
                "   group by L1.code_ss150 " +

                "   union all " +

                "   select " +
                "     L13.code_ss150, " +
                "     sum(coalesce(p13.power, 0) - " +
                "        case " +
                "          when exists(select id from cn.ncn_techterms where id_pack = p13.id and id_proposal not in (2, 3)) " +
                "            then " +
                "              (select coalesce(t13.pow_exist, 0) from cn.ncn_techterms t13 " +
                "                where t13.id_pack = p13.id) " +
                "            else 0 " +
                "        end " +
                "      ) as power_will_connect, " +
                "     ('ПРИСОЕДИНЕНИЕ с 01.08.2010: ' || string_agg(p13.id::text, ', ')) as packs_id " +
                "   from cn.ncn_enlines L13, cn.ncn_packages p13 " +
                "   where L13.code_ss150 is not null " +
                "    and (p13.id_pack_status in (1, 2, 5) " +
                "      or (p13.id_pack_status in (3, 4) and coalesce(p13.is_reserv, 0) = 1) " +
                "         and p13.id not in (select id_pack from cn.ncn_movement " +
                "         where id_movement_status = 1 and id_state in (1001000, 1001223, 1000022, 1000817, " +
                "           1000813, 1001421, 1000156, 1000)))" +

                /*
                "   and L13.id_pack in (select distinct(id_pack) from cn.ncn_movement where is_completed = 1 " +
                "     and id_state in (1000158, 2000041)) " +
                */

                "   and ((L13.id_pack not in (select id_pack from cn.ncn_techterms " +
                "          where coalesce(is_realized, -1) = 1) " +
                "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                "            where spl2cn.id_cn_pack = p13.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_NEWCONNECTION +
                "            and spl2cn.id_spl_pack in (select splp.id " +
                "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                "              and splp.id = spl2cn.id_spl_pack " +
                "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                "                and splm.id_state in (select id " +
                "                  from cn.spl_states where id_state_status = 2) " +
                //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                " ))) " +
                "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                "            where pp2cn.id_cn_pack = p13.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_NEWCONNECTION +
                "            and pp2cn.id_pp_pack in (select ppp.id " +
                "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                "              and ppp.id = pp2cn.id_pp_pack " +
                "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                "                and ppm.id_state in (select id " +
                "                  from cn.pp_states where id_state_status = 2) " +
                //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                " ))))) " +

                "   and p13.id = L13.id_pack " +
                "   and L13.code_ss150 in (" + substCode + ")" + //NET-4352
                "   group by L13.code_ss150 " +

                "   union all " +

                "   select " +
                "     L18.code_ss150, " +
                "     sum(coalesce(p18.power, 0) - " +
                "        case " +
                "          when exists(select id from cn.cn_20110314_techterms where id_pack = p18.id and id_proposal not in (2, 3)) " +
                "            then " +
                "              (select coalesce(t18.pow_exist, 0) from cn.cn_20110314_techterms t18 " +
                "                where t18.id_pack = p18.id) " +
                "            else 0 " +
                "        end " +
                "      ) as power_will_connect, " +
                "     ('ПРИСОЕДИНЕНИЕ с 14.03.2011: ' || string_agg(p18.id::text, ', ')) as packs_id " +
                "   from cn.cn_20110314_enlines L18, cn.cn_20110314_packages p18 " +
                "   where L18.code_ss150 is not null " +
                "    and (p18.id_pack_status in (1, 2, 5) " +
                "      or (p18.id_pack_status in (3, 4) and coalesce(p18.is_reserv, 0) = 1) " +
                "         and p18.id not in (select id_pack from cn.cn_20110314_movement " +
                "         where id_movement_status = 1 and id_state in (1000156, 1001223, 1001421, 1001000, 1000)))" +

                "   and (L18.id_pack in (select distinct(id_pack) from cn.cn_20110314_movement where is_completed = 1 " +
                "       and id_state in (1000150, 1000158, 2000034, 2000041)) " +
                "     or L18.id_pack in (select id from cn.cn_20110314_packages " +
                "       where coalesce(reg_num_tu_contract, '') <> ''))" +

                "   and ((L18.id_pack not in (select id_pack from cn.cn_20110314_techterms " +
                "          where coalesce(is_realized, -1) = 1) " +
                "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                "            where spl2cn.id_cn_pack = p18.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION_20110314 +
                "            and spl2cn.id_spl_pack in (select splp.id " +
                "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                "              and splp.id = spl2cn.id_spl_pack " +
                "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                "                and splm.id_state in (select id " +
                "                  from cn.spl_states where id_state_status = 2) " +
                //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                " ))) " +
                "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                "            where pp2cn.id_cn_pack = p18.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION_20110314 +
                "            and pp2cn.id_pp_pack in (select ppp.id " +
                "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                "              and ppp.id = pp2cn.id_pp_pack " +
                "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                "                and ppm.id_state in (select id " +
                "                  from cn.pp_states where id_state_status = 2) " +
                //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                " ))))) " +

                "   and p18.id = L18.id_pack " +
                "   and L18.code_ss150 in (" + substCode + ")" + //NET-4352
                "   group by L18.code_ss150 " +

                "   union all " +

                "   select " +
                "     L20.code_ss150, " +
                "     sum(coalesce(p20.power, 0) - " +
                "        case " +
                "          when exists(select id from cn.eap_techterms where id_pack = p20.id and id_proposal not in (2, 3)) " +
                "            then " +
                "              (select coalesce(t20.pow_exist, 0) from cn.eap_techterms t20 " +
                "                where t20.id_pack = p20.id) " +
                "            else 0 " +
                "        end " +
                "      ) as power_will_connect, " +
                "     ('ПРИСОЕДИНЕНИЕ с 01.03.2013: ' || string_agg(p20.id::text, ', ')) as packs_id " +
                "   from cn.eap_enlines L20, cn.eap_packages p20 " +
                "   where L20.code_ss150 is not null " +
                "    and (p20.id_pack_status in (1, 2, 5) " +
                "      or (p20.id_pack_status in (3, 4) and coalesce(p20.is_reserv, 0) = 1) " +
                "         and p20.id not in (select id_pack from cn.eap_movement " +
                "         where id_movement_status = 1 and id_state in (1000)))" +

                "   and (L20.id_pack in (select distinct(id_pack) from cn.eap_movement where is_completed = 1 " +
                "       and id_state in (1050028, 1050032, 1070026, 1070030, 2050011, 2050014)) " +
                "     or L20.id_pack in (select id from cn.eap_packages " +
                "       where coalesce(reg_num_tu_contract, '') <> ''))" +

                "   and ((L20.id_pack not in (select id_pack from cn.eap_techterms " +
                "          where coalesce(is_realized, -1) = 1) " +
                "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                "            where spl2cn.id_cn_pack = p20.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER +
                "            and spl2cn.id_spl_pack in (select splp.id " +
                "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                "              and splp.id = spl2cn.id_spl_pack " +
                "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                "                and splm.id_state in (select id " +
                "                  from cn.spl_states where id_state_status = 2) " +
                //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                " ))) " +
                "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                "            where pp2cn.id_cn_pack = p20.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER +
                "            and pp2cn.id_pp_pack in (select ppp.id " +
                "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                "              and ppp.id = pp2cn.id_pp_pack " +
                "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                "                and ppm.id_state in (select id " +
                "                  from cn.pp_states where id_state_status = 2) " +
                //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                " ))))) " +

                "   and p20.id = L20.id_pack " +
                "   and L20.code_ss150 in (" + substCode + ")" + //NET-4352
                "   group by L20.code_ss150 " +

                " ) s150 " +

                " group by s150.code_ss150 " +

                /* Исключено избыточное упорядочивание по коду подстанции -
                * в подчинённом запросе максимум только одна строка: */
                //" order by s150.code_ss150 " +

                " ) connections_power";
            }

            else if (s04typecode.doubleValue() == 2)
            {
                query = " /* Присоединяемые мощности */ " +
                    /* Агрегирование запроса для случая пустой выборки
                    * и расчёт резерва мощности только как суммы входящих параметров -
                    * номинальной и замерянной мощностей */

                    " select " +
                    "  cast(" + tpCode + " as double precision) as code_substation150, " +
                    "  sum(connections_power.pvtu) as pvtu, " +
                    "  case " +
                    "    when sum(connections_power.prezerv) is not Null " +
                    "      then sum(connections_power.prezerv) " +
                    "    else " +
                    "      coalesce(coalesce(" + maxadmispower + ", 0) - " +
                    "      coalesce(" + gauge_power + ", 0)) " +
                    "  end as prezerv, " +
                    "  string_agg(connections_power.packs_id::text, '. ') as packs_id, " +

                    "  case " +
                    "    when sum(connections_power.prezerv) is not Null " +
                    "      then sum(connections_power.prezerv) * 250 " +
                    "    else " +
                    "      coalesce(coalesce(" + maxadmispower + ", 0) - " +
                    "      coalesce(" + gauge_power + ", 0)) * 250 " +
                    "  end as prezervcost " +

                    " from " +
                    " ( " +

                    /* Выборка суммы присоединяемых мощностей к
                    * ПС 150 / 35 / 10 - 6 кВ и расчёт резерва мощности как
                    * суммы входящих параметров - номинальной и замерянной мощностей,
                    * за вычетом суммы присоединяемых мощностей */

                    " select " +
                    " s150.code_substation150, " +
                    " (coalesce(sum(s150.power_will_connect),0) / coalesce(" + tr_cnt + ", 1)) as pvtu, " +

                    /**
                    *   Prezerv(кВт) = Pnom(кВт) - [Pisn(кВт) + Pvtu(кВт)]
                    *   Pnom(кВт) = [0.85 * s150nominalpower(кВА)](кВт)
                    *   Pisn(кВт) = gauge_power(кВт)
                    */

                    " coalesce(coalesce(" + maxadmispower + ",0) - " +
                    "     (coalesce(" + gauge_power + ",0) + coalesce(sum(s150.power_will_connect),0) / coalesce(" + tr_cnt + ", 1)),0) as prezerv, " +
                    " string_agg(s150.packs_id::text, '; ') as packs_id" +

                    " from " +
                    "   (select " +
                    "     L1.code_substation150, " +
                    "     sum(coalesce(p1.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.cn_techterms where id_pack = p1.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t1.pow_exist, 0) from cn.cn_techterms t1 " +
                    "                where t1.id_pack = p1.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ: ' || string_agg(p1.id::text, ', ')) as packs_id " +
                    "   from cn.cn_enlines L1, cn.cn_packages p1 " +
                    "   where L1.code_substation150 is not null " +
                    "    and (p1.id_pack_status in (1, 2, 5) " +
                    "      or (p1.id_pack_status in (3, 4) and coalesce(p1.is_reserv, 0) = 1) " +
                    "         and p1.id not in (select id_pack from cn.cn_movement " +
                    "         where id_movement_status = 1 and id_state in (1148, 1001000, 1001421, 1001223, " +
                    "           100062, 100012, 1000156, 1169, 1000022, 1152, 1000813, 100022, 1172, 1000817)))" +

                    /*
                    "     and L1.id_pack in (select distinct(id_pack) from cn.cn_movement where is_completed = 1 " +
                    "     and id_state in (1000158, 2000041)) " +
                    */

                    "   and ((L1.id_pack not in (select id_pack from cn.cn_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p1.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p1.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p1.id = L1.id_pack " +
                    "   and L1.code_substation150 in (" + substCode + ")" + //NET-4352
                    "   group by L1.code_substation150 " +

                    "   union all " +

                    "   select " +
                    "     L13.code_substation150, " +
                    "     sum(coalesce(p13.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.ncn_techterms where id_pack = p13.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t13.pow_exist, 0) from cn.ncn_techterms t13 " +
                    "                where t13.id_pack = p13.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ с 01.08.2010: ' || string_agg(p13.id::text, ', ')) as packs_id " +
                    "   from cn.ncn_enlines L13, cn.ncn_packages p13 " +
                    "   where L13.code_substation150 is not null " +
                    "    and (p13.id_pack_status in (1, 2, 5) " +
                    "      or (p13.id_pack_status in (3, 4) and coalesce(p13.is_reserv, 0) = 1) " +
                    "         and p13.id not in (select id_pack from cn.ncn_movement " +
                    "         where id_movement_status = 1 and id_state in (1001000, 1001223, 1000022, 1000817, " +
                    "           1000813, 1001421, 1000156, 1000)))" +

                    /*
                    "   and L13.id_pack in (select distinct(id_pack) from cn.ncn_movement where is_completed = 1 " +
                    "     and id_state in (1000158, 2000041)) " +
                    */

                    "   and ((L13.id_pack not in (select id_pack from cn.ncn_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p13.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_NEWCONNECTION +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p13.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_NEWCONNECTION +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p13.id = L13.id_pack " +
                    "   and L13.code_substation150 in (" + substCode + ")" + //NET-4352
                    "   group by L13.code_substation150 " +

                    "   union all " +

                    "   select " +
                    "     L18.code_substation150, " +
                    "     sum(coalesce(p18.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.cn_20110314_techterms where id_pack = p18.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t18.pow_exist, 0) from cn.cn_20110314_techterms t18 " +
                    "                where t18.id_pack = p18.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ с 14.03.2011: ' || string_agg(p18.id::text, ', ')) as packs_id " +
                    "   from cn.cn_20110314_enlines L18, cn.cn_20110314_packages p18 " +
                    "   where L18.code_substation150 is not null " +
                    "    and (p18.id_pack_status in (1, 2, 5) " +
                    "      or (p18.id_pack_status in (3, 4) and coalesce(p18.is_reserv, 0) = 1) " +
                    "         and p18.id not in (select id_pack from cn.cn_20110314_movement " +
                    "         where id_movement_status = 1 and id_state in (1000156, 1001223, 1001421, 1001000, 1000)))" +

                    "   and (L18.id_pack in (select distinct(id_pack) from cn.cn_20110314_movement where is_completed = 1 " +
                    "       and id_state in (1000150, 1000158, 2000034, 2000041)) " +
                    "     or L18.id_pack in (select id from cn.cn_20110314_packages " +
                    "       where coalesce(reg_num_tu_contract, '') <> ''))" +

                    "   and ((L18.id_pack not in (select id_pack from cn.cn_20110314_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p18.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION_20110314 +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p18.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION_20110314 +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p18.id = L18.id_pack " +
                    "   and L18.code_substation150 in (" + substCode + ")" + //NET-4352
                    "   group by L18.code_substation150 " +

                    "   union all " +

                    "   select " +
                    "     L20.code_substation150, " +
                    "     sum(coalesce(p20.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.eap_techterms where id_pack = p20.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t20.pow_exist, 0) from cn.eap_techterms t20 " +
                    "                where t20.id_pack = p20.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ с 01.03.2013: ' || string_agg(p20.id::text, ', ')) as packs_id " +
                    "   from cn.eap_enlines L20, cn.eap_packages p20 " +
                    "   where L20.code_substation150 is not null " +
                    "    and (p20.id_pack_status in (1, 2, 5) " +
                    "      or (p20.id_pack_status in (3, 4) and coalesce(p20.is_reserv, 0) = 1) " +
                    "         and p20.id not in (select id_pack from cn.eap_movement " +
                    "         where id_movement_status = 1 and id_state in (1000)))" +

                    "   and (L20.id_pack in (select distinct(id_pack) from cn.eap_movement where is_completed = 1 " +
                    "       and id_state in (1050028, 1050032, 1070026, 1070030, 2050011, 2050014)) " +
                    "     or L20.id_pack in (select id from cn.eap_packages " +
                    "       where coalesce(reg_num_tu_contract, '') <> ''))" +

                    "   and ((L20.id_pack not in (select id_pack from cn.eap_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p20.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p20.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p20.id = L20.id_pack " +
                    "   and L20.code_substation150 in (" + substCode + ")" + //NET-4352
                    "   group by L20.code_substation150 " +

                    " ) s150 " +

                    " group by s150.code_substation150 " +

                    /* Исключено избыточное упорядочивание по коду подстанции -
                    * в подчинённом запросе максимум только одна строка: */
                    //" order by s150.code_substation150 " +

                    " ) connections_power";
            }
            else if (s04typecode.doubleValue() >= 3)
            {
                query = " /* Присоединяемые мощности */ " +

                    /* Агрегирование запроса для случая пустой выборки
                    * и расчёт резерва мощности только как суммы входящих параметров -
                    * номинальной и замерянной мощностей */

                    " select " +
                    "  cast(" + tpCode + " as double precision) as code_substation04, " +
                    "  sum(connections_power.pvtu) as pvtu, " +
                    "  case " +
                    "    when sum(connections_power.prezerv) is not Null " +
                    "      then sum(connections_power.prezerv) " +
                    "    else " +
                    "      coalesce(coalesce(" + maxadmispower + ", 0) - " +
                    "      coalesce(" + gauge_power + ", 0)) " +
                    "  end as prezerv, " +
                    "  string_agg(connections_power.packs_id::text, '. ') as packs_id, " +

                    "  case " +
                    "    when sum(connections_power.prezerv) is not Null " +
                    "      then sum(connections_power.prezerv) * 250 " +
                    "    else " +
                    "      coalesce(coalesce(" + maxadmispower + ", 0) - " +
                    "      coalesce(" + gauge_power + ", 0)) * 250 " +
                    "  end as prezervcost " +

                    " from " +
                    " ( " +

                    /* Выборка суммы присоединяемых мощностей к
                    * ТП 10 - 6 / 0,4 кВ и расчёт резерва мощности как
                    * суммы входящих параметров - номинальной и замерянной мощностей,
                    * за вычетом суммы присоединяемых мощностей */

                    " select " +
                    " s04.code_substation04, " +
                    " coalesce(sum(s04.power_will_connect),0) as pvtu, " +

                    /**
                    *   Prezerv(кВт) = Pnom(кВт) - [Pisn(кВт) + Pvtu(кВт)]
                    *   Pnom(кВт) = [0.85 * s04nominalpower(кВА)](кВт)
                    *   Pisn(кВт) = gauge_power(кВт)
                    */

                    " coalesce(coalesce(" + maxadmispower + ",0) - " +
                    "     (coalesce(" + gauge_power + ",0) + coalesce(sum(s04.power_will_connect),0)),0) as prezerv, " +
                    " string_agg(s04.packs_id::text, '; ') as packs_id" +

                    " from " +
                    "   (select " +
                    "     L1.code_substation04, " +
                    "     sum(coalesce(p1.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.cn_techterms where id_pack = p1.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t1.pow_exist, 0) from cn.cn_techterms t1 " +
                    "                where t1.id_pack = p1.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ: ' || string_agg(p1.id::text, ', ')) as packs_id " +
                    "   from cn.cn_enlines L1, cn.cn_packages p1 " +
                    "   where L1.code_substation04 is not null " +
                    "    and (p1.id_pack_status in (1, 2, 5) " +
                    "      or (p1.id_pack_status in (3, 4) and coalesce(p1.is_reserv, 0) = 1) " +
                    "         and p1.id not in (select id_pack from cn.cn_movement " +
                    "         where id_movement_status = 1 and id_state in (1148, 1001000, 1001421, 1001223, " +
                    "           100062, 100012, 1000156, 1169, 1000022, 1152, 1000813, 100022, 1172, 1000817)))" +

                    /*
                    "     and L1.id_pack in (select distinct(id_pack) from cn.cn_movement where is_completed = 1 " +
                    "     and id_state in (1000158, 2000041)) " +
                    */

                    "   and ((L1.id_pack not in (select id_pack from cn.cn_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p1.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p1.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p1.id = L1.id_pack " +
                    "   and L1.code_substation04 = " + tpCode +
                    "   group by L1.code_substation04 " +

                    "   union all " +

                    "   select " +
                    "     L13.code_substation04, " +
                    "     sum(coalesce(p13.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.ncn_techterms where id_pack = p13.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t13.pow_exist, 0) from cn.ncn_techterms t13 " +
                    "                where t13.id_pack = p13.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ с 01.08.2010: ' || string_agg(p13.id::text, ', ')) as packs_id " +
                    "   from cn.ncn_enlines L13, cn.ncn_packages p13 " +
                    "   where L13.code_substation04 is not null " +
                    "    and (p13.id_pack_status in (1, 2, 5) " +
                    "      or (p13.id_pack_status in (3, 4) and coalesce(p13.is_reserv, 0) = 1) " +
                    "         and p13.id not in (select id_pack from cn.ncn_movement " +
                    "         where id_movement_status = 1 and id_state in (1001000, 1001223, 1000022, 1000817, " +
                    "           1000813, 1001421, 1000156, 1000)))" +

                    /*
                    "   and L13.id_pack in (select distinct(id_pack) from cn.ncn_movement where is_completed = 1 " +
                    "     and id_state in (1000158, 2000041)) " +
                    */

                    "   and ((L13.id_pack not in (select id_pack from cn.ncn_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p13.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_NEWCONNECTION +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p13.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_NEWCONNECTION +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p13.id = L13.id_pack " +
                    "   and L13.code_substation04 = " + tpCode +
                    "   group by L13.code_substation04 " +

                    "   union all " +

                    "   select " +
                    "     L18.code_substation04, " +
                    "     sum(coalesce(p18.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.cn_20110314_techterms where id_pack = p18.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t18.pow_exist, 0) from cn.cn_20110314_techterms t18 " +
                    "                where t18.id_pack = p18.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ с 14.03.2011: ' || string_agg(p18.id::text, ', ')) as packs_id " +
                    "   from cn.cn_20110314_enlines L18, cn.cn_20110314_packages p18 " +
                    "   where L18.code_substation04 is not null " +
                    "    and (p18.id_pack_status in (1, 2, 5) " +
                    "      or (p18.id_pack_status in (3, 4) and coalesce(p18.is_reserv, 0) = 1) " +
                    "         and p18.id not in (select id_pack from cn.cn_20110314_movement " +
                    "         where id_movement_status = 1 and id_state in (1000156, 1001223, 1001421, 1001000, 1000)))" +

                    "   and (L18.id_pack in (select distinct(id_pack) from cn.cn_20110314_movement where is_completed = 1 " +
                    "       and id_state in (1000150, 1000158, 2000034, 2000041)) " +
                    "     or L18.id_pack in (select id from cn.cn_20110314_packages " +
                    "       where coalesce(reg_num_tu_contract, '') <> ''))" +

                    "   and ((L18.id_pack not in (select id_pack from cn.cn_20110314_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p18.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION_20110314 +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p18.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION_20110314 +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p18.id = L18.id_pack " +
                    "   and L18.code_substation04 = " + tpCode +
                    "   group by L18.code_substation04 " +

                    "   union all " +

                    "   select " +
                    "     L20.code_substation04, " +
                    "     sum(coalesce(p20.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.eap_techterms where id_pack = p20.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t20.pow_exist, 0) from cn.eap_techterms t20 " +
                    "                where t20.id_pack = p20.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ с 01.03.2013: ' || string_agg(p20.id::text, ', ')) as packs_id " +
                    "   from cn.eap_enlines L20, cn.eap_packages p20 " +
                    "   where L20.code_substation04 is not null " +
                    "    and (p20.id_pack_status in (1, 2, 5) " +
                    "      or (p20.id_pack_status in (3, 4) and coalesce(p20.is_reserv, 0) = 1) " +
                    "         and p20.id not in (select id_pack from cn.eap_movement " +
                    "         where id_movement_status = 1 and id_state in (1000)))" +

                    "   and (L20.id_pack in (select distinct(id_pack) from cn.eap_movement where is_completed = 1 " +
                    "       and id_state in (1050028, 1050032, 1070026, 1070030, 2050011, 2050014)) " +
                    "     or L20.id_pack in (select id from cn.eap_packages " +
                    "       where coalesce(reg_num_tu_contract, '') <> ''))" +

                    "   and ((L20.id_pack not in (select id_pack from cn.eap_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p20.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p20.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p20.id = L20.id_pack " +
                    "   and L20.code_substation04 = " + tpCode +
                    "   group by L20.code_substation04 " +

                    " ) s04 " +

                    " group by s04.code_substation04 " +

                    /* Исключено избыточное упорядочивание по коду подстанции -
                    * в подчинённом запросе максимум только одна строка: */
                    //" order by s04.code_substation04 " +

                    " ) connections_power";

            }

            if (cnConnection == null || cnConnection.isClosed()){
                cnConnection = getConnection(AuthorizationJNDINames.CN_DATASOURCE);
            }

            Statement tempSt;
            tempSt = cnConnection.createStatement();
            ResultSet rs = tempSt.executeQuery(query);

            while(rs.next()) {
                {
                    HashMap hashMap = new HashMap();
                    hashMap.put(powerRezervDS.pVTU, rs.getBigDecimal(2));
                    hashMap.put(powerRezervDS.pREZERV, rs.getBigDecimal(3));
                    hashMap.put(powerRezervDS.packs_id, rs.getString(4));
                    hashMap.put(powerRezervDS.pREZERVcost, rs.getBigDecimal(5));
                    rows.add(hashMap);
                }
            }

            rs.close();
            tempSt.close();

            return new powerRezervDS(rows.toArray());

        } catch (SQLException e) {
            throw new ReportSystemException(e);
        } catch (DatasourceConnectException e) {
            throw new ReportSystemException(e);
        } finally {
            try {
                if (cnConnection != null && !cnConnection.isClosed()) {
                    cnConnection.close();
                }
            } catch (SQLException e) {
            }
        }
    }


    public JRDataSource getConnectedAndReservPowerByResolution115(
            int tpCode, Date gauge_date,
            BigDecimal maxadmispower,
            BigDecimal blsum,
            Double s04typecode,
            BigDecimal tr_cnt,
            BigDecimal countTU) throws PersistenceException {
        try {

            /** коэффициент использования номинальной мощности */
            BigDecimal Kisp = new BigDecimal(1);

            JRFillParameter jrParameterMap = this.parametersMap.get("REPORT_PARAMETERS_MAP");
            UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");

            if (netConnection == null || netConnection.isClosed()) {
                netConnection = getNetConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
            }

            PriconnectionDataLogic logic = new PriconnectionDataLogic(netConnection, userProfile);

            if (blsum == null) {
                blsum = new BigDecimal(0);
            }
            if (countTU == null) {
                countTU = new BigDecimal(0);
            }

            if (maxadmispower.doubleValue() > 0 && blsum.doubleValue() > 0
                    && countTU.doubleValue() > 0) {
                Kisp = logic.getKisp(countTU, Integer.MIN_VALUE, s04typecode.intValue());
            } else {
                Kisp = new BigDecimal(1);
            }

            ArrayList rows  = new ArrayList();

            String query = "";

            //http://10.77.11.28:8080/browse/NET-4352
            //По причине представления пользователями строки таблицы высоковольтных подстанций
            //не как понижающей станции в целом, а как отдельного трансформатора этой станции,
            //появились дублированные записи номенклатурной таблицы net.substation150
            //подсистемы ОБЪЕКТОВ ЭНЕРГЕТИКИ комплекса EnergyNet.
            //Поэтому появилась таблица net.ad2substation, связывающая высоковольтные подстанции
            //из подаваемой диспетчерами таблицы загруженности высоковольтных станций net.ad4subst150
            //с высоковольтными станциями справочной таблицы net.substation150,
            //что влияет на публикуемый отчёт о Резервах мощностей.
            //В связи с этим на уровне сервера приложений проекта EnergyNet
            //в данном файле CNScriptlet.java изменились функции
            //getConnectedAndReservPowerByResolution115, getConnectedPowerByMaxAdmis
            String substCode = "";
            if (s04typecode.doubleValue() == 1 || s04typecode.doubleValue() == 2)
            {
	            String selectStr = "select net.group_concat(cast(a2s.substationcode as varchar), ', ') " +
	              " from net.ad2substation a2s where a2s.adcode in ( " +
	              "   select ad2st.adcode from net.ad2substation ad2st " +
	              "   where ad2st.substationcode = " + tpCode + ")";
	            PreparedStatement statement = netConnection.prepareStatement(selectStr);
	            ResultSet set = statement.executeQuery();
	            if (set.next()) {
	              substCode = set.getString(1);}
	            set.close();
	            statement.close();
            }

            if (s04typecode.doubleValue() == 1)
            {
                query = " /* Присоединяемые мощности */ " +
                /* Агрегирование запроса для случая пустой выборки
                * и расчёт резерва мощности только как суммы входящих параметров -
                * номинальной и замерянной мощностей */

                " select " +
                "  cast(" + tpCode + " as double precision) as code_ss150, " +
                "  coalesce(sum(connections_power.pvtu),0) as pvtu, " +

				" coalesce(coalesce(" + maxadmispower + "/" + Kisp + ", 0) - "
						+ " (coalesce(" + blsum + ", 0) + coalesce(sum(connections_power.pvtu),0))) as prezerv, " +

                /*
                "  case " +
                "    when sum(connections_power.prezerv) is not Null " +
                "      then sum(connections_power.prezerv) " +
                "    else " +
                "      coalesce(coalesce(" + maxadmispower + "/" + Kisp + ", 0) - " +
                "      coalesce(" + blsum + ", 0)) " +
                "  end as prezerv, " +
                */

                /*
                "  case " +
                "    when sum(connections_power.prezerv) is not Null " +
                "      then sum(connections_power.prezerv) * 250 " +
                "    else " +
                "      coalesce(coalesce(" + maxadmispower + "/" + Kisp + ", 0) - " +
                "      coalesce(" + blsum + ", 0)) * 250 " +
                "  end as prezervcost, " +
                */

                " 250 as prezervcost, " +

                "  string_agg(connections_power.packs_id::text, '. ') as packs_id " +

                " from " +
                " ( " +

                /* Выборка суммы присоединяемых мощностей к
                * ПС 150 / 35 / 10 - 6 кВ и расчёт резерва мощности как
                * суммы входящих параметров - номинальной и замерянной мощностей,
                * за вычетом суммы присоединяемых мощностей */



                " select " +
                " s150.code_ss150, " +
                " (coalesce(sum(s150.power_will_connect),0) / coalesce(" + tr_cnt + ", 1)) as pvtu, " +

                /**
                *   Prezerv(кВт) = Pnom(кВт) - [Pisn(кВт) + Pvtu(кВт)]
                *   Pnom(кВт) = [0.85 * s150nominalpower(кВА)](кВт)
                *   Pisn(кВт) = gauge_power(кВт)
                */

                " coalesce(coalesce(" + maxadmispower + "/" + Kisp + ",0) - " +
                "     (coalesce(" + blsum + ",0) + coalesce(sum(s150.power_will_connect),0) / coalesce(" + tr_cnt + ", 1)),0) as prezerv, " +
                " string_agg(s150.packs_id::text, '; ') as packs_id" +

                " from " +
                "   (select " +
                "     L1.code_ss150, " +
                "     sum(coalesce(p1.power, 0) - " +
                "        case " +
                "          when exists(select id from cn.cn_techterms where id_pack = p1.id and id_proposal not in (2, 3)) " +
                "            then " +
                "              (select coalesce(t1.pow_exist, 0) from cn.cn_techterms t1 " +
                "                where t1.id_pack = p1.id) " +
                "            else 0 " +
                "        end " +
                "      ) as power_will_connect, " +
                "     ('ПРИСОЕДИНЕНИЕ: ' || string_agg(p1.id::text, ', ')) as packs_id " +
                "   from cn.cn_enlines L1, cn.cn_packages p1 " +
                "   where L1.code_ss150 is not null " +
                "    and (p1.id_pack_status in (1, 2, 5) " +
                "      or (p1.id_pack_status in (3, 4) and coalesce(p1.is_reserv, 0) = 1) " +
                "         and p1.id not in (select id_pack from cn.cn_movement " +
                "         where id_movement_status = 1 and id_state in (1148, 1001000, 1001421, 1001223, " +
                "           100062, 100012, 1000156, 1169, 1000022, 1152, 1000813, 100022, 1172, 1000817)))" +

                /*
                "     and L1.id_pack in (select distinct(id_pack) from cn.cn_movement where is_completed = 1 " +
                "     and id_state in (1000158, 2000041)) " +
                */

                "   and ((L1.id_pack not in (select id_pack from cn.cn_techterms " +
                "          where coalesce(is_realized, -1) = 1) " +
                "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                "            where spl2cn.id_cn_pack = p1.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION +
                "            and spl2cn.id_spl_pack in (select splp.id " +
                "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                "              and splp.id = spl2cn.id_spl_pack " +
                "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                "                and splm.id_state in (select id " +
                "                  from cn.spl_states where id_state_status = 2) " +
                //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                " ))) " +
                "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                "            where pp2cn.id_cn_pack = p1.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION +
                "            and pp2cn.id_pp_pack in (select ppp.id " +
                "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                "              and ppp.id = pp2cn.id_pp_pack " +
                "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                "                and ppm.id_state in (select id " +
                "                  from cn.pp_states where id_state_status = 2) " +
                //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                " ))))) " +

                "   and p1.id = L1.id_pack " +
                "   and L1.code_ss150 in (" + substCode + ")" + //NET-4352
                "   group by L1.code_ss150 " +

                "   union all " +

                "   select " +
                "     L13.code_ss150, " +
                "     sum(coalesce(p13.power, 0) - " +
                "        case " +
                "          when exists(select id from cn.ncn_techterms where id_pack = p13.id and id_proposal not in (2, 3)) " +
                "            then " +
                "              (select coalesce(t13.pow_exist, 0) from cn.ncn_techterms t13 " +
                "                where t13.id_pack = p13.id) " +
                "            else 0 " +
                "        end " +
                "      ) as power_will_connect, " +
                "     ('ПРИСОЕДИНЕНИЕ с 01.08.2010: ' || string_agg(p13.id::text, ', ')) as packs_id " +
                "   from cn.ncn_enlines L13, cn.ncn_packages p13 " +
                "   where L13.code_ss150 is not null " +
                "    and (p13.id_pack_status in (1, 2, 5) " +
                "      or (p13.id_pack_status in (3, 4) and coalesce(p13.is_reserv, 0) = 1) " +
                "         and p13.id not in (select id_pack from cn.ncn_movement " +
                "         where id_movement_status = 1 and id_state in (1001000, 1001223, 1000022, 1000817, " +
                "           1000813, 1001421, 1000156, 1000)))" +

                /*
                "   and L13.id_pack in (select distinct(id_pack) from cn.ncn_movement where is_completed = 1 " +
                "     and id_state in (1000158, 2000041)) " +
                */

                "   and ((L13.id_pack not in (select id_pack from cn.ncn_techterms " +
                "          where coalesce(is_realized, -1) = 1) " +
                "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                "            where spl2cn.id_cn_pack = p13.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_NEWCONNECTION +
                "            and spl2cn.id_spl_pack in (select splp.id " +
                "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                "              and splp.id = spl2cn.id_spl_pack " +
                "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                "                and splm.id_state in (select id " +
                "                  from cn.spl_states where id_state_status = 2) " +
                //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                " ))) " +
                "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                "            where pp2cn.id_cn_pack = p13.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_NEWCONNECTION +
                "            and pp2cn.id_pp_pack in (select ppp.id " +
                "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                "              and ppp.id = pp2cn.id_pp_pack " +
                "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                "                and ppm.id_state in (select id " +
                "                  from cn.pp_states where id_state_status = 2) " +
                //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                " ))))) " +

                "   and p13.id = L13.id_pack " +
                "   and L13.code_ss150 in (" + substCode + ")" + //NET-4352
                "   group by L13.code_ss150 " +

                "   union all " +

                "   select " +
                "     L18.code_ss150, " +
                "     sum(coalesce(p18.power, 0) - " +
                "        case " +
                "          when exists(select id from cn.cn_20110314_techterms where id_pack = p18.id and id_proposal not in (2, 3)) " +
                "            then " +
                "              (select coalesce(t18.pow_exist, 0) from cn.cn_20110314_techterms t18 " +
                "                where t18.id_pack = p18.id) " +
                "            else 0 " +
                "        end " +
                "      ) as power_will_connect, " +
                "     ('ПРИСОЕДИНЕНИЕ с 14.03.2011: ' || string_agg(p18.id::text, ', ')) as packs_id " +
                "   from cn.cn_20110314_enlines L18, cn.cn_20110314_packages p18 " +
                "   where L18.code_ss150 is not null " +
                "    and (p18.id_pack_status in (1, 2, 5) " +
                "      or (p18.id_pack_status in (3, 4) and coalesce(p18.is_reserv, 0) = 1) " +
                "         and p18.id not in (select id_pack from cn.cn_20110314_movement " +
                "         where id_movement_status = 1 and id_state in (1000156, 1001223, 1001421, 1001000, 1000)))" +

                "   and (L18.id_pack in (select distinct(id_pack) from cn.cn_20110314_movement where is_completed = 1 " +
                "       and id_state in (1000150, 1000158, 2000034, 2000041)) " +
                "     or L18.id_pack in (select id from cn.cn_20110314_packages " +
                "       where coalesce(reg_num_tu_contract, '') <> ''))" +

                "   and ((L18.id_pack not in (select id_pack from cn.cn_20110314_techterms " +
                "          where coalesce(is_realized, -1) = 1) " +
                "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                "            where spl2cn.id_cn_pack = p18.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION_20110314 +
                "            and spl2cn.id_spl_pack in (select splp.id " +
                "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                "              and splp.id = spl2cn.id_spl_pack " +
                "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                "                and splm.id_state in (select id " +
                "                  from cn.spl_states where id_state_status = 2) " +
                //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                " ))) " +
                "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                "            where pp2cn.id_cn_pack = p18.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION_20110314 +
                "            and pp2cn.id_pp_pack in (select ppp.id " +
                "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                "              and ppp.id = pp2cn.id_pp_pack " +
                "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                "                and ppm.id_state in (select id " +
                "                  from cn.pp_states where id_state_status = 2) " +
                //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                " ))))) " +

                "   and p18.id = L18.id_pack " +
                "   and L18.code_ss150 in (" + substCode + ")" + //NET-4352
                "   group by L18.code_ss150 " +

                "   union all " +

                "   select " +
                "     L20.code_ss150, " +
                "     sum(coalesce(p20.power, 0) - " +
                "        case " +
                "          when exists(select id from cn.eap_techterms where id_pack = p20.id and id_proposal not in (2, 3)) " +
                "            then " +
                "              (select coalesce(t20.pow_exist, 0) from cn.eap_techterms t20 " +
                "                where t20.id_pack = p20.id) " +
                "            else 0 " +
                "        end " +
                "      ) as power_will_connect, " +
                "     ('ПРИСОЕДИНЕНИЕ с 01.03.2013: ' || string_agg(p20.id::text, ', ')) as packs_id " +
                "   from cn.eap_enlines L20, cn.eap_packages p20 " +
                "   where L20.code_ss150 is not null " +
                "    and (p20.id_pack_status in (1, 2, 5) " +
                "      or (p20.id_pack_status in (3, 4) and coalesce(p20.is_reserv, 0) = 1) " +
                "         and p20.id not in (select id_pack from cn.eap_movement " +
                "         where id_movement_status = 1 and id_state in (1000)))" +

                "   and (L20.id_pack in (select distinct(id_pack) from cn.eap_movement where is_completed = 1 " +
                "       and id_state in (1050028, 1050032, 1070026, 1070030, 2050011, 2050014)) " +
                "     or L20.id_pack in (select id from cn.eap_packages " +
                "       where coalesce(reg_num_tu_contract, '') <> ''))" +

                "   and ((L20.id_pack not in (select id_pack from cn.eap_techterms " +
                "          where coalesce(is_realized, -1) = 1) " +
                "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                "            where spl2cn.id_cn_pack = p20.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER +
                "            and spl2cn.id_spl_pack in (select splp.id " +
                "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                "              and splp.id = spl2cn.id_spl_pack " +
                "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                "                and splm.id_state in (select id " +
                "                  from cn.spl_states where id_state_status = 2) " +
                //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                " ))) " +
                "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                "            where pp2cn.id_cn_pack = p20.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER +
                "            and pp2cn.id_pp_pack in (select ppp.id " +
                "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                "              and ppp.id = pp2cn.id_pp_pack " +
                "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                "                and ppm.id_state in (select id " +
                "                  from cn.pp_states where id_state_status = 2) " +
                //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                " ))))) " +

                "   and p20.id = L20.id_pack " +
                "   and L20.code_ss150 in (" + substCode + ")" + //NET-4352
                "   group by L20.code_ss150 " +

                " ) s150 " +

                " group by s150.code_ss150 " +

                /* Исключено избыточное упорядочивание по коду подстанции -
                * в подчинённом запросе максимум только одна строка: */
                //" order by s150.code_ss150 " +

                " ) connections_power";
            }

            else if (s04typecode.doubleValue() == 2)
            {
                query = " /* Присоединяемые мощности */ " +
                    /* Агрегирование запроса для случая пустой выборки
                    * и расчёт резерва мощности только как суммы входящих параметров -
                    * номинальной и замерянной мощностей */

                    " select " +
                    "  cast(" + tpCode + " as double precision) as code_substation150, " +
                    "  coalesce(sum(connections_power.pvtu),0) as pvtu, " +


					" coalesce(coalesce(" + maxadmispower + "/" + Kisp + ", 0) - "
						+ " (coalesce(" + blsum + ", 0) + coalesce(sum(connections_power.pvtu),0))) as prezerv, " +

                    /*
                    "  case " +
                    "    when sum(connections_power.prezerv) is not Null " +
                    "      then sum(connections_power.prezerv) " +
                    "    else " +
                    "      coalesce(coalesce(" + maxadmispower + "/" + Kisp + ", 0) - " +
                    "      coalesce(" + blsum + ", 0)) " +
                    "  end as prezerv, " +
                    */

                    /*
                    "  case " +
                    "    when sum(connections_power.prezerv) is not Null " +
                    "      then sum(connections_power.prezerv) * 250 " +
                    "    else " +
                    "      coalesce(coalesce(" + maxadmispower  + "/" + Kisp + ", 0) - " +
                    "      coalesce(" + blsum + ", 0)) * 250 " +
                    "  end as prezervcost, " +
                    */


                    " 250 as prezervcost, " +

                    "  string_agg(connections_power.packs_id::text, '. ') as packs_id " +

                    " from " +
                    " ( " +

                    /* Выборка суммы присоединяемых мощностей к
                    * ПС 150 / 35 / 10 - 6 кВ и расчёт резерва мощности как
                    * суммы входящих параметров - номинальной и замерянной мощностей,
                    * за вычетом суммы присоединяемых мощностей */

                    " select " +
                    " s150.code_substation150, " +
                    " (coalesce(sum(s150.power_will_connect),0) / coalesce(" + tr_cnt + ", 1)) as pvtu, " +

                    /**
                    *   Prezerv(кВт) = Pnom(кВт) - [Pisn(кВт) + Pvtu(кВт)]
                    *   Pnom(кВт) = [0.85 * s150nominalpower(кВА)](кВт)
                    *   Pisn(кВт) = gauge_power(кВт)
                    */

                    " coalesce(coalesce(" + maxadmispower + "/" + Kisp + ",0) - " +
                    "     (coalesce(" + blsum + ",0) + coalesce(sum(s150.power_will_connect),0) / coalesce(" + tr_cnt + ", 1)),0) as prezerv, " +
                    " string_agg(s150.packs_id::text, '; ') as packs_id" +

                    " from " +
                    "   (select " +
                    "     L1.code_substation150, " +
                    "     sum(coalesce(p1.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.cn_techterms where id_pack = p1.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t1.pow_exist, 0) from cn.cn_techterms t1 " +
                    "                where t1.id_pack = p1.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ: ' || string_agg(p1.id::text, ', ')) as packs_id " +
                    "   from cn.cn_enlines L1, cn.cn_packages p1 " +
                    "   where L1.code_substation150 is not null " +
                    "    and (p1.id_pack_status in (1, 2, 5) " +
                    "      or (p1.id_pack_status in (3, 4) and coalesce(p1.is_reserv, 0) = 1) " +
                    "         and p1.id not in (select id_pack from cn.cn_movement " +
                    "         where id_movement_status = 1 and id_state in (1148, 1001000, 1001421, 1001223, " +
                    "           100062, 100012, 1000156, 1169, 1000022, 1152, 1000813, 100022, 1172, 1000817)))" +

                    /*
                    "     and L1.id_pack in (select distinct(id_pack) from cn.cn_movement where is_completed = 1 " +
                    "     and id_state in (1000158, 2000041)) " +
                    */

                    "   and ((L1.id_pack not in (select id_pack from cn.cn_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p1.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p1.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p1.id = L1.id_pack " +
                    "   and L1.code_substation150 in (" + substCode + ")" + //NET-4352
                    "   group by L1.code_substation150 " +

                    "   union all " +

                    "   select " +
                    "     L13.code_substation150, " +
                    "     sum(coalesce(p13.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.ncn_techterms where id_pack = p13.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t13.pow_exist, 0) from cn.ncn_techterms t13 " +
                    "                where t13.id_pack = p13.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ с 01.08.2010: ' || string_agg(p13.id::text, ', ')) as packs_id " +
                    "   from cn.ncn_enlines L13, cn.ncn_packages p13 " +
                    "   where L13.code_substation150 is not null " +
                    "    and (p13.id_pack_status in (1, 2, 5) " +
                    "      or (p13.id_pack_status in (3, 4) and coalesce(p13.is_reserv, 0) = 1) " +
                    "         and p13.id not in (select id_pack from cn.ncn_movement " +
                    "         where id_movement_status = 1 and id_state in (1001000, 1001223, 1000022, 1000817, " +
                    "           1000813, 1001421, 1000156, 1000)))" +

                    /*
                    "   and L13.id_pack in (select distinct(id_pack) from cn.ncn_movement where is_completed = 1 " +
                    "     and id_state in (1000158, 2000041)) " +
                    */

                    "   and ((L13.id_pack not in (select id_pack from cn.ncn_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p13.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_NEWCONNECTION +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p13.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_NEWCONNECTION +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p13.id = L13.id_pack " +
                    "   and L13.code_substation150 in (" + substCode + ")" + //NET-4352
                    "   group by L13.code_substation150 " +

                    "   union all " +

                    "   select " +
                    "     L18.code_substation150, " +
                    "     sum(coalesce(p18.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.cn_20110314_techterms where id_pack = p18.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t18.pow_exist, 0) from cn.cn_20110314_techterms t18 " +
                    "                where t18.id_pack = p18.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ с 14.03.2011: ' || string_agg(p18.id::text, ', ')) as packs_id " +
                    "   from cn.cn_20110314_enlines L18, cn.cn_20110314_packages p18 " +
                    "   where L18.code_substation150 is not null " +
                    "    and (p18.id_pack_status in (1, 2, 5) " +
                    "      or (p18.id_pack_status in (3, 4) and coalesce(p18.is_reserv, 0) = 1) " +
                    "         and p18.id not in (select id_pack from cn.cn_20110314_movement " +
                    "         where id_movement_status = 1 and id_state in (1000156, 1001223, 1001421, 1001000, 1000)))" +

                    "   and (L18.id_pack in (select distinct(id_pack) from cn.cn_20110314_movement where is_completed = 1 " +
                    "       and id_state in (1000150, 1000158, 2000034, 2000041)) " +
                    "     or L18.id_pack in (select id from cn.cn_20110314_packages " +
                    "       where coalesce(reg_num_tu_contract, '') <> ''))" +

                    "   and ((L18.id_pack not in (select id_pack from cn.cn_20110314_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p18.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION_20110314 +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p18.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION_20110314 +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p18.id = L18.id_pack " +
                    "   and L18.code_substation150 in (" + substCode + ")" + //NET-4352
                    "   group by L18.code_substation150 " +

                    "   union all " +

                    "   select " +
                    "     L20.code_substation150, " +
                    "     sum(coalesce(p20.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.eap_techterms where id_pack = p20.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t20.pow_exist, 0) from cn.eap_techterms t20 " +
                    "                where t20.id_pack = p20.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ с 01.03.2013: ' || string_agg(p20.id::text, ', ')) as packs_id " +
                    "   from cn.eap_enlines L20, cn.eap_packages p20 " +
                    "   where L20.code_substation150 is not null " +
                    "    and (p20.id_pack_status in (1, 2, 5) " +
                    "      or (p20.id_pack_status in (3, 4) and coalesce(p20.is_reserv, 0) = 1) " +
                    "         and p20.id not in (select id_pack from cn.eap_movement " +
                    "         where id_movement_status = 1 and id_state in (1000)))" +

                    "   and (L20.id_pack in (select distinct(id_pack) from cn.eap_movement where is_completed = 1 " +
                    "       and id_state in (1050028, 1050032, 1070026, 1070030, 2050011, 2050014)) " +
                    "     or L20.id_pack in (select id from cn.eap_packages " +
                    "       where coalesce(reg_num_tu_contract, '') <> ''))" +

                    "   and ((L20.id_pack not in (select id_pack from cn.eap_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p20.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p20.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p20.id = L20.id_pack " +
                    "   and L20.code_substation150 in (" + substCode + ")" + //NET-4352
                    "   group by L20.code_substation150 " +

                    " ) s150 " +

                    " group by s150.code_substation150 " +

                    /* Исключено избыточное упорядочивание по коду подстанции -
                    * в подчинённом запросе максимум только одна строка: */
                    //" order by s150.code_substation150 " +

                    " ) connections_power";
            }
            else if (s04typecode.doubleValue() >= 3)
            {
                query = " /* Присоединяемые мощности */ " +

                    /* Агрегирование запроса для случая пустой выборки
                    * и расчёт резерва мощности только как суммы входящих параметров -
                    * номинальной и замерянной мощностей */

                    " select " +
                    "  cast(" + tpCode + " as double precision) as code_substation04, " +
                    "  coalesce(sum(connections_power.pvtu),0) as pvtu, " +

					" coalesce(coalesce(" + maxadmispower + "/" + Kisp + ", 0) - "
						+ " (coalesce(" + blsum + ", 0) + coalesce(sum(connections_power.pvtu),0))) as prezerv, " +

                    /*
                    "  case " +
                    "    when sum(connections_power.prezerv) is not Null " +
                    "      then sum(connections_power.prezerv) " +
                    "    else " +
                    "      coalesce(coalesce(" + maxadmispower + "/" + Kisp + ", 0) - " +
                    "      coalesce(" + blsum + ", 0)) " +
                    "  end as prezerv, " +
                    */

                    /*
                    "  case " +
                    "    when sum(connections_power.prezerv) is not Null " +
                    "      then sum(connections_power.prezerv) * 250 " +
                    "    else " +
                    "      coalesce(coalesce(" + maxadmispower  + "/" + Kisp + ", 0) - " +
                    "      coalesce(" + blsum + ", 0)) * 250 " +
                    "  end as prezervcost, " +
                    */


                    " 250 as prezervcost, " +

                    "  string_agg(connections_power.packs_id::text, '. ') as packs_id " +


                    " from " +
                    " ( " +

                    /* Выборка суммы присоединяемых мощностей к
                    * ТП 10 - 6 / 0,4 кВ и расчёт резерва мощности как
                    * суммы входящих параметров - номинальной и замерянной мощностей,
                    * за вычетом суммы присоединяемых мощностей */

                    " select " +
                    " s04.code_substation04, " +
                    " coalesce(sum(s04.power_will_connect),0) as pvtu, " +

                    /**
                    *   Prezerv(кВт) = Pnom(кВт) - [Pisn(кВт) + Pvtu(кВт)]
                    *   Pnom(кВт) = [0.85 * s04nominalpower(кВА)](кВт)
                    *   Pisn(кВт) = gauge_power(кВт)
                    */

                    " coalesce(coalesce(" + maxadmispower + "/" + Kisp + ",0) - " +
                    "     (coalesce(" + blsum + ",0) + coalesce(sum(s04.power_will_connect),0)),0) as prezerv, " +
                    " string_agg(s04.packs_id::text, '; ') as packs_id" +

                    " from " +
                    "   (select " +
                    "     L1.code_substation04, " +
                    "     sum(coalesce(p1.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.cn_techterms where id_pack = p1.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t1.pow_exist, 0) from cn.cn_techterms t1 " +
                    "                where t1.id_pack = p1.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ: ' || string_agg(p1.id::text, ', ')) as packs_id " +
                    "   from cn.cn_enlines L1, cn.cn_packages p1 " +
                    "   where L1.code_substation04 is not null " +
                    "    and (p1.id_pack_status in (1, 2, 5) " +
                    "      or (p1.id_pack_status in (3, 4) and coalesce(p1.is_reserv, 0) = 1) " +
                    "         and p1.id not in (select id_pack from cn.cn_movement " +
                    "         where id_movement_status = 1 and id_state in (1148, 1001000, 1001421, 1001223, " +
                    "           100062, 100012, 1000156, 1169, 1000022, 1152, 1000813, 100022, 1172, 1000817)))" +

                    /*
                    "     and L1.id_pack in (select distinct(id_pack) from cn.cn_movement where is_completed = 1 " +
                    "     and id_state in (1000158, 2000041)) " +
                    */

                    "   and ((L1.id_pack not in (select id_pack from cn.cn_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p1.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p1.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p1.id = L1.id_pack " +
                    "   and L1.code_substation04 = " + tpCode +
                    "   group by L1.code_substation04 " +

                    "   union all " +

                    "   select " +
                    "     L13.code_substation04, " +
                    "     sum(coalesce(p13.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.ncn_techterms where id_pack = p13.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t13.pow_exist, 0) from cn.ncn_techterms t13 " +
                    "                where t13.id_pack = p13.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ с 01.08.2010: ' || string_agg(p13.id::text, ', ')) as packs_id " +
                    "   from cn.ncn_enlines L13, cn.ncn_packages p13 " +
                    "   where L13.code_substation04 is not null " +
                    "    and (p13.id_pack_status in (1, 2, 5) " +
                    "      or (p13.id_pack_status in (3, 4) and coalesce(p13.is_reserv, 0) = 1) " +
                    "         and p13.id not in (select id_pack from cn.ncn_movement " +
                    "         where id_movement_status = 1 and id_state in (1001000, 1001223, 1000022, 1000817, " +
                    "           1000813, 1001421, 1000156, 1000)))" +

                    /*
                    "   and L13.id_pack in (select distinct(id_pack) from cn.ncn_movement where is_completed = 1 " +
                    "     and id_state in (1000158, 2000041)) " +
                    */

                    "   and ((L13.id_pack not in (select id_pack from cn.ncn_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p13.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_NEWCONNECTION +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p13.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_NEWCONNECTION +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p13.id = L13.id_pack " +
                    "   and L13.code_substation04 = " + tpCode +
                    "   group by L13.code_substation04 " +

                    "   union all " +

                    "   select " +
                    "     L18.code_substation04, " +
                    "     sum(coalesce(p18.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.cn_20110314_techterms where id_pack = p18.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t18.pow_exist, 0) from cn.cn_20110314_techterms t18 " +
                    "                where t18.id_pack = p18.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ с 14.03.2011: ' || string_agg(p18.id::text, ', ')) as packs_id " +
                    "   from cn.cn_20110314_enlines L18, cn.cn_20110314_packages p18 " +
                    "   where L18.code_substation04 is not null " +
                    "    and (p18.id_pack_status in (1, 2, 5) " +
                    "      or (p18.id_pack_status in (3, 4) and coalesce(p18.is_reserv, 0) = 1) " +
                    "         and p18.id not in (select id_pack from cn.cn_20110314_movement " +
                    "         where id_movement_status = 1 and id_state in (1000156, 1001223, 1001421, 1001000, 1000)))" +

                    "   and (L18.id_pack in (select distinct(id_pack) from cn.cn_20110314_movement where is_completed = 1 " +
                    "       and id_state in (1000150, 1000158, 2000034, 2000041)) " +
                    "     or L18.id_pack in (select id from cn.cn_20110314_packages " +
                    "       where coalesce(reg_num_tu_contract, '') <> ''))" +

                    "   and ((L18.id_pack not in (select id_pack from cn.cn_20110314_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p18.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION_20110314 +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p18.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION_20110314 +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p18.id = L18.id_pack " +
                    "   and L18.code_substation04 = " + tpCode +
                    "   group by L18.code_substation04 " +

                    "   union all " +

                    "   select " +
                    "     L20.code_substation04, " +
                    "     sum(coalesce(p20.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.eap_techterms where id_pack = p20.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t20.pow_exist, 0) from cn.eap_techterms t20 " +
                    "                where t20.id_pack = p20.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ с 01.03.2013: ' || string_agg(p20.id::text, ', ')) as packs_id " +
                    "   from cn.eap_enlines L20, cn.eap_packages p20 " +
                    "   where L20.code_substation04 is not null " +
                    "    and (p20.id_pack_status in (1, 2, 5) " +
                    "      or (p20.id_pack_status in (3, 4) and coalesce(p20.is_reserv, 0) = 1) " +
                    "         and p20.id not in (select id_pack from cn.eap_movement " +
                    "         where id_movement_status = 1 and id_state in (1000)))" +

                    "   and (L20.id_pack in (select distinct(id_pack) from cn.eap_movement where is_completed = 1 " +
                    "       and id_state in (1050028, 1050032, 1070026, 1070030, 2050011, 2050014)) " +
                    "     or L20.id_pack in (select id from cn.eap_packages " +
                    "       where coalesce(reg_num_tu_contract, '') <> ''))" +

                    "   and ((L20.id_pack not in (select id_pack from cn.eap_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p20.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p20.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p20.id = L20.id_pack " +
                    "   and L20.code_substation04 = " + tpCode +
                    "   group by L20.code_substation04 " +

                    " ) s04 " +

                    " group by s04.code_substation04 " +

                    /* Исключено избыточное упорядочивание по коду подстанции -
                    * в подчинённом запросе максимум только одна строка: */
                    //" order by s04.code_substation04 " +

                    " ) connections_power";

            }

            if (cnConnection == null || cnConnection.isClosed()) {
                cnConnection = getConnection(AuthorizationJNDINames.CN_DATASOURCE);
            }

            Statement tempSt;
            tempSt = cnConnection.createStatement();
            ResultSet rs = tempSt.executeQuery(query);

            while(rs.next()) {
                {
                    HashMap hashMap = new HashMap();
                    hashMap.put(powerRezervDS.pVTU, rs.getBigDecimal(2));
                    hashMap.put(powerRezervDS.pREZERV, rs.getBigDecimal(3));
                    hashMap.put(powerRezervDS.pREZERVcost, rs.getBigDecimal(4));
                    hashMap.put(powerRezervDS.packs_id, rs.getString(5));
                    hashMap.put(powerRezervDS.kisp, Kisp);

                    rows.add(hashMap);
                }
            }

            rs.close();
            tempSt.close();

            return new powerRezervDS(rows.toArray());

        } catch (SQLException e) {
            throw new ReportSystemException(e);
        } catch (DatasourceConnectException e) {
            throw new ReportSystemException(e);
        } finally {
            try {
                if (netConnection != null && !netConnection.isClosed()) {
                    netConnection.close();
                }
                if (cnConnection != null && !cnConnection.isClosed()) {
                    cnConnection.close();
                }
            } catch (SQLException e) {
            }
        }
    }



    /**
    *  Определение присоединяемой мощности
    *
    *  @param tpCode - код подстанции
    *  @param gauge_date - дата замера
    *
    */

    public JRDataSource getConnectedPowerCurrent(
            int tpCode, Date gauge_date) {
        try {

            ArrayList rows  = new ArrayList();
            String query = " /* Присоединяемые мощности */ " +

                    /* Агрегирование запроса для случая пустой выборки
                    * и расчёт резерва мощности только как суммы входящих параметров -
                    * номинальной и замерянной мощностей */

                    " select " +
                    "  cast(" + tpCode + " as double precision) as code_substation04, " +
                    "  sum(connections_power.pvtu) as pvtu, " +

                    " from " +
                    " ( " +

                    /* Выборка суммы присоединяемых мощностей к
                    * ТП 10 - 6 / 0,4 кВ и расчёт резерва мощности как
                    * суммы входящих параметров - номинальной и замерянной мощностей,
                    * за вычетом суммы присоединяемых мощностей */

                    " select " +
                    " s04.code_substation04, " +
                    " coalesce(sum(s04.power_will_connect),0) as pvtu " +

                    " from " +
                    "   (select " +
                    "     L1.code_substation04, " +
                    "     sum(coalesce(p1.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.cn_techterms where id_pack = p1.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t1.pow_exist, 0) from cn.cn_techterms t1 " +
                    "                where t1.id_pack = p1.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ: ' || string_agg(p1.id::text, ', ')) as packs_id " +
                    "   from cn.cn_enlines L1, cn.cn_packages p1 " +
                    "   where L1.code_substation04 is not null " +
                    "    and (p1.id_pack_status in (1, 2, 5) " +
                    "      or (p1.id_pack_status in (3, 4) and coalesce(p1.is_reserv, 0) = 1) " +
                    "         and p1.id not in (select id_pack from cn.cn_movement " +
                    "         where id_movement_status = 1 and id_state in (1148, 1001000, 1001421, 1001223, " +
                    "           100062, 100012, 1000156, 1169, 1000022, 1152, 1000813, 100022, 1172, 1000817)))" +

                    /*
                    "     and L1.id_pack in (select distinct(id_pack) from cn.cn_movement where is_completed = 1 " +
                    "     and id_state in (1000158, 2000041)) " +
                    */

                    "   and ((L1.id_pack not in (select id_pack from cn.cn_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p1.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p1.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p1.id = L1.id_pack " +
                    "   and L1.code_substation04 = " + tpCode +
                    "   group by L1.code_substation04 " +

                    "   union all " +

                    "   select " +
                    "     L13.code_substation04, " +
                    "     sum(coalesce(p13.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.ncn_techterms where id_pack = p13.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t13.pow_exist, 0) from cn.ncn_techterms t13 " +
                    "                where t13.id_pack = p13.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ с 01.08.2010: ' || string_agg(p13.id::text, ', ')) as packs_id " +
                    "   from cn.ncn_enlines L13, cn.ncn_packages p13 " +
                    "   where L13.code_substation04 is not null " +
                    "    and (p13.id_pack_status in (1, 2, 5) " +
                    "      or (p13.id_pack_status in (3, 4) and coalesce(p13.is_reserv, 0) = 1) " +
                    "         and p13.id not in (select id_pack from cn.ncn_movement " +
                    "         where id_movement_status = 1 and id_state in (1001000, 1001223, 1000022, 1000817, " +
                    "           1000813, 1001421, 1000156, 1000)))" +

                    /*
                    "   and L13.id_pack in (select distinct(id_pack) from cn.ncn_movement where is_completed = 1 " +
                    "     and id_state in (1000158, 2000041)) " +
                    */

                    "   and ((L13.id_pack not in (select id_pack from cn.ncn_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p13.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_NEWCONNECTION +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p13.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_NEWCONNECTION +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p13.id = L13.id_pack " +
                    "   and L13.code_substation04 = " + tpCode +
                    "   group by L13.code_substation04 " +

                    "   union all " +

                    "   select " +
                    "     L18.code_substation04, " +
                    "     sum(coalesce(p18.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.cn_20110314_techterms where id_pack = p18.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t18.pow_exist, 0) from cn.cn_20110314_techterms t18 " +
                    "                where t18.id_pack = p18.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ с 14.03.2011: ' || string_agg(p18.id::text, ', ')) as packs_id " +
                    "   from cn.cn_20110314_enlines L18, cn.cn_20110314_packages p18 " +
                    "   where L18.code_substation04 is not null " +
                    "    and (p18.id_pack_status in (1, 2, 5) " +
                    "      or (p18.id_pack_status in (3, 4) and coalesce(p18.is_reserv, 0) = 1) " +
                    "         and p18.id not in (select id_pack from cn.cn_20110314_movement " +
                    "         where id_movement_status = 1 and id_state in (1000156, 1001223, 1001421, 1001000, 1000)))" +

                    "   and (L18.id_pack in (select distinct(id_pack) from cn.cn_20110314_movement where is_completed = 1 " +
                    "       and id_state in (1000150, 1000158, 2000034, 2000041)) " +
                    "     or L18.id_pack in (select id from cn.cn_20110314_packages " +
                    "       where coalesce(reg_num_tu_contract, '') <> ''))" +

                    "   and ((L18.id_pack not in (select id_pack from cn.cn_20110314_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p18.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION_20110314 +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p18.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_CONNECTION_20110314 +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p18.id = L18.id_pack " +
                    "   and L18.code_substation04 = " + tpCode +
                    "   group by L18.code_substation04 " +

                    "   union all " +

                    "   select " +
                    "     L20.code_substation04, " +
                    "     sum(coalesce(p20.power, 0) - " +
                    "        case " +
                    "          when exists(select id from cn.eap_techterms where id_pack = p20.id and id_proposal not in (2, 3)) " +
                    "            then " +
                    "              (select coalesce(t20.pow_exist, 0) from cn.eap_techterms t20 " +
                    "                where t20.id_pack = p20.id) " +
                    "            else 0 " +
                    "        end " +
                    "      ) as power_will_connect, " +
                    "     ('ПРИСОЕДИНЕНИЕ с 01.03.2013: ' || string_agg(p20.id::text, ', ')) as packs_id " +
                    "   from cn.eap_enlines L20, cn.eap_packages p20 " +
                    "   where L20.code_substation04 is not null " +
                    "    and (p20.id_pack_status in (1, 2, 5) " +
                    "      or (p20.id_pack_status in (3, 4) and coalesce(p20.is_reserv, 0) = 1) " +
                    "         and p20.id not in (select id_pack from cn.eap_movement " +
                    "         where id_movement_status = 1 and id_state in (1000)))" +

                    "   and (L20.id_pack in (select distinct(id_pack) from cn.eap_movement where is_completed = 1 " +
                    "       and id_state in (1050028, 1050032, 1070026, 1070030, 2050011, 2050014)) " +
                    "     or L20.id_pack in (select id from cn.eap_packages " +
                    "       where coalesce(reg_num_tu_contract, '') <> ''))" +

                    "   and ((L20.id_pack not in (select id_pack from cn.eap_techterms " +
                    "          where coalesce(is_realized, -1) = 1) " +
                    "           and not exists (select spl2cn.id from cn.spl2cn spl2cn " +
                    "            where spl2cn.id_cn_pack = p20.id and spl2cn.id_subsystem_cn = " + CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER +
                    "            and spl2cn.id_spl_pack in (select splp.id " +
                    "              from cn.spl_packages splp where splp.id_pack_status = 2 " +
                    "              and splp.id = spl2cn.id_spl_pack " +
                    "              and splp.id in (select splm.id_pack from cn.spl_movement splm " +
                    "                where splm.id_pack = splp.id and splm.id_movement_status = 1 " +
                    "                and splm.id_state in (select id " +
                    "                  from cn.spl_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))) " +
                    "         and not exists (select pp2cn.id from cn.pp2cn pp2cn " +
                    "            where pp2cn.id_cn_pack = p20.id and pp2cn.id_subsystem_cn = " + CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER +
                    "            and pp2cn.id_pp_pack in (select ppp.id " +
                    "              from cn.pp_packages ppp where ppp.id_pack_status = 2 " +
                    "              and ppp.id = pp2cn.id_pp_pack " +
                    "              and ppp.id in (select ppm.id_pack from cn.pp_movement ppm " +
                    "                where ppm.id_pack = ppp.id and ppm.id_movement_status = 1 " +
                    "                and ppm.id_state in (select id " +
                    "                  from cn.pp_states where id_state_status = 2) " +
                    //"                and startdate <=  " + Tools.convertDateToSQLString(gauge_date) +
                    " ))))) " +

                    "   and p20.id = L20.id_pack " +
                    "   and L20.code_substation04 = " + tpCode +
                    "   group by L20.code_substation04 " +

                    " ) s04 " +

                    " group by s04.code_substation04 " +

                    /* Исключено избыточное упорядочивание по коду подстанции -
                    * в подчинённом запросе максимум только одна строка: */
                    //" order by s04.code_substation04 " +

                    " ) connections_power";


            if (cnConnection == null || cnConnection.isClosed()){
                cnConnection = getConnection(AuthorizationJNDINames.CN_DATASOURCE);
            }

            Statement tempSt;
            tempSt = cnConnection.createStatement();
            ResultSet rs = tempSt.executeQuery(query);

            while(rs.next()) {
                {
                    HashMap hashMap = new HashMap();
                    hashMap.put(powerRezervDS.pVTU, rs.getBigDecimal(2));
                    rows.add(hashMap);
                }
            }

            rs.close();
            tempSt.close();

            return new powerRezervDS(rows.toArray());

        } catch (SQLException e) {
            throw new ReportSystemException(e);
        } catch (DatasourceConnectException e) {
            throw new ReportSystemException(e);
        } finally {
            try {
                if (cnConnection != null && !cnConnection.isClosed()) {
                    cnConnection.close();
                }
            } catch (SQLException e) {
            }
        }
    }


    /**
    *
    *  Определение резерва мощности на подстанции
    *
    *  Pi = Pnom * 1/Kisp - sum(Pdog)
    *
    *  @param s04nominalpower - номинальная мощность
    *  @param gauge_power - сумма присоединенных мощностей
    *  @param contTU - количество потребителей
    *
    */

    public BigDecimal getPrezerv(BigDecimal s04nominalpower,
            BigDecimal gauge_power, BigDecimal countTU) {

        BigDecimal pRezerv = new BigDecimal(0);

        /** коэффициент использования номинальной мощности */
        BigDecimal Kisp = new BigDecimal(0);

        if (s04nominalpower.doubleValue() > 0
                && gauge_power.doubleValue() > 0
                && countTU.doubleValue() > 0) {

            /**
            *
            *   старое(доброе) уравнение прямой A(x1, y1) и B(x2, y2)...
            *   k = (y2-y1)/(x2-x1)
            *   b = y1 - k*x1
            *   y = kx+b
            *
            *   Kisp1_10        : A(1, 0.85) : B(10, 0.65)
            *   Kisp10_50       : A(10, 0.65) : B(50, 0.6)
            *   Kisp50_100      : A(50, 0.6) : B(100, 0.5)
            *   Kisp100_250     : A(100, 0.5) : B(250, 0.4)
            *   Kisp250_750     : A(250, 0.4) : B(750, 0.2)
            *   Kisp750_1000    : = 0.2
            *   Kisp1000_10000  : A(1000, 0.2) : B(10000, 0.1)
            *   Kisp_more_10000 : = 0.1
            */


            double x1 = 0;
            double x2 = 0;
            double y1 = 0;
            double y2 = 0;

            if (countTU.doubleValue() >= 1 && countTU.doubleValue() <= 10) {
                x1 = 1; y1 = 0.85;  x2 = 10; y2 = 0.65;
                Kisp = new BigDecimal(((y2-y1)/(x2-x1)) * countTU.doubleValue()
                        + (y1 - (y2-y1)/(x2-x1) * x1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            }

            if (countTU.doubleValue() > 10 && countTU.doubleValue() <= 50) {
                x1 = 10; y1 = 0.65;  x2 = 50; y2 = 0.6;
                Kisp = new BigDecimal(((y2-y1)/(x2-x1)) * countTU.doubleValue()
                        + (y1 - (y2-y1)/(x2-x1) * x1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            }

            if (countTU.doubleValue() > 50 && countTU.doubleValue() <= 100) {
                x1 = 50; y1 = 0.6;  x2 = 100; y2 = 0.5;
                Kisp = new BigDecimal(((y2-y1)/(x2-x1)) * countTU.doubleValue()
                        + (y1 - (y2-y1)/(x2-x1) * x1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            }

            if (countTU.doubleValue() > 100 && countTU.doubleValue() <= 250) {
                x1 = 100; y1 = 0.5;  x2 = 250; y2 = 0.4;
                Kisp = new BigDecimal(((y2-y1)/(x2-x1)) * countTU.doubleValue()
                        + (y1 - (y2-y1)/(x2-x1) * x1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            }

            if (countTU.doubleValue() > 250 && countTU.doubleValue() <= 750) {
                x1 = 250; y1 = 0.4;  x2 = 750; y2 = 0.2;
                Kisp = new BigDecimal(((y2-y1)/(x2-x1)) * countTU.doubleValue()
                        + (y1 - (y2-y1)/(x2-x1) * x1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            }

            if (countTU.doubleValue() > 750 && countTU.doubleValue() <= 1000) {
                Kisp = new BigDecimal(0.2);
            }

            if (countTU.doubleValue() > 1000 && countTU.doubleValue() <= 10000) {
                x1 = 1000; y1 = 0.2;  x2 = 10000; y2 = 0.1;
                Kisp = new BigDecimal(((y2-y1)/(x2-x1)) * countTU.doubleValue()
                        + (y1 - (y2-y1)/(x2-x1) * x1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            }

            if (countTU.doubleValue() > 10000) {
                Kisp = new BigDecimal(0.1);
            }

            pRezerv = (s04nominalpower.multiply(new BigDecimal(1/Kisp.doubleValue()))).subtract(gauge_power);

        }

        return pRezerv;
    }


    /**
    *  Определение предполагаемой мощности по коду пакета
    *
    *  @param packCode - код пакета, код подсистемы
    *  @param subsystemCode - код подсистемы
    *
    */

    public BigDecimal getConnectedPower(int packCode, int subsystemCode) {
        try {

            BigDecimal connectedPower = new BigDecimal(0);
            String query = "";

            switch (subsystemCode) {
            case (CNSubsystemType.SS_CONNECTION): {
                query = "select tension_point from cn.cn_techterms where id_pack = " + packCode;
                break;
            }
            case (CNSubsystemType.SS_NEWCONNECTION): {
                query = "select tension_point from cn.ncn_techterms where id_pack = " + packCode;
                break;
            }
            case (CNSubsystemType.SS_CONNECTION_20110314): {
                query = "select tension_point from cn.cn_20110314_techterms where id_pack = " + packCode;
                break;
            }
            case (CNSubsystemType.SS_ELECTRICINSTALLACCESSPOWER): {
                query = "select tension_point from cn.eap_techterms where id_pack = " + packCode;
                break;
            }
            }

            if (query.equals("")) {
                return connectedPower;
            }

            if (cnConnection == null || cnConnection.isClosed()) {
                cnConnection = getConnection(AuthorizationJNDINames.CN_DATASOURCE);
            }

            Statement tempSt;
            tempSt = cnConnection.createStatement();
            ResultSet rs = tempSt.executeQuery(query);

            while (rs.next()) {
                connectedPower = rs.getBigDecimal(1);
            }

            rs.close();
            tempSt.close();

            return connectedPower;

        } catch (SQLException e) {
            throw new ReportSystemException(e);
        } catch (DatasourceConnectException e) {
            throw new ReportSystemException(e);
        } finally {
            try {
                if (cnConnection != null && !cnConnection.isClosed()) {
                    cnConnection.close();
                }
            } catch (SQLException e) {
            }
        }
    }

}
