package com.ksoe.energynet.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import com.ksoe.energynet.logic.ENConsts;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.lla.persistence.exception.SystemException;

public class Tools {

	public static final String ENERGY_NET_SERVER_IP = "10.77.11.18";
	public static final String AUTHORIZATION_SERVER_IP = "10.77.11.16";
	public static final String BILLING_SERVER_IP = "10.77.11.41";

	public static final DateFormat dateFormatDefault = new SimpleDateFormat("dd.MM.yyyy");


	public interface Transformator<Z, T> {
		Z transform(T value);
	}

	private static Date date31129999;
	static {
		Calendar c = Calendar.getInstance();
		c.set(9999, Calendar.JANUARY, 31, 0, 0, 0);
		Tools.date31129999 = c.getTime();
	}

	public static final Date getDateOf31129999() {
		return Tools.date31129999;
	}

	  /**
	   *
	   * Устанавливает время даты равное 00:00:00
	   *
	   * @param date дата с установленным временем
	   * @return дата без времени
	   */
	  public static Date clearTimeOfDate(Date date)
	  {
		  Calendar calendar = Calendar.getInstance();

	      calendar.setTime(date);
		  calendar.clear(Calendar.SECOND);
		  calendar.clear(Calendar.MILLISECOND);
		  calendar.clear(Calendar.HOUR);
		  calendar.clear(Calendar.MINUTE);
		  calendar.clear(Calendar.HOUR_OF_DAY);

	      calendar.set(Calendar.SECOND, 0);
	      calendar.set(Calendar.MILLISECOND, 0);
	      calendar.set(Calendar.HOUR, 0);
	      calendar.set(Calendar.MINUTE, 0);
	      calendar.set(Calendar.HOUR_OF_DAY, 0);

	      return calendar.getTime();
	  }

	  /**
	   * Возвращает дату по заданным году, месяцу и дню (время = 00:00:00)
	   *
	   * @param year - год
	   * @param month - месяц (в человеческом формате от 1 до 12)
	   * @param day - день (от 1 до 31)
	   *
	   * @return дата, у которой год = year, месяц = month, день = day и время = 00:00:00
	   */
	  public static Date encodeDate(int year, int month, int day)
	  {
			Calendar calendar = Calendar.getInstance();

			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, month - 1);
			calendar.set(Calendar.DATE, day);

			return clearTimeOfDate(calendar.getTime());
	  }

	  /**
	   * Возвращает дату начала декады
	   *
	   * @param year - год
	   * @param month - месяц (в человеческом формате от 1 до 12)
	   * @param decadeNumber - номер декады (1, 2 или 3)
	   *
	   * @return дата начала декады
	   */
	  public static Date getDecadeStart(int year, int month, int decadeNumber)
	  {
		  int day = Integer.MIN_VALUE;

		  switch(decadeNumber)
		  {
			  case 1:
				  day = 1;
				  break;
			  case 2:
				  day = 11;
				  break;
			  case 3:
				  day = 21;
				  break;
			  default:
				  throw new EnergyproSystemException("\n\nНекорректный номер декады: " + decadeNumber);
		  }

		  return encodeDate(year, month, day);
	  }

	  /**
	   * Возвращает дату окончания декады
	   *
	   * @param year - год
	   * @param month - месяц (в человеческом формате от 1 до 12)
	   * @param decadeNumber - номер декады (1, 2 или 3)
	   *
	   * @return дата окончания декады
	   */
	  public static Date getDecadeEnd(int year, int month, int decadeNumber)
	  {
		  int day = Integer.MIN_VALUE;

		  switch(decadeNumber)
		  {
			  case 1:
				  day = 10;
				  break;
			  case 2:
				  day = 20;
				  break;
			  case 3:
				  return getLastDayOfMonth(encodeDate(year, month, 1));
			  default:
				  throw new EnergyproSystemException("\n\nНекорректный номер декады: " + decadeNumber);
		  }

		  return encodeDate(year, month, day);
	  }

	  /**
	   *
	   * Возвращает украинское наименование месяца по его номеру в году
	   *
	   * @param monthNumber номер месяца
	   * @return String украинское наименование месяца
	   */
	  public static String getUkrNameOfMonth(int monthNumber)
	  {
		  String name = "";
		  switch(monthNumber)
		  {
		  case 1:
			  name = "Січень";
			  break;
		  case 2:
			  name = "Лютий";
			  break;
		  case 3:
			  name = "Березень";
			  break;
		  case 4:
			  name = "Квітень";
			  break;
		  case 5:
			  name = "Травень";
			  break;
		  case 6:
			  name = "Червень";
			  break;
		  case 7:
			  name = "Липень";
			  break;
		  case 8:
			  name = "Серпень";
			  break;
		  case 9:
			  name = "Вересень";
			  break;
		  case 10:
			  name = "Жовтень";
			  break;
		  case 11:
			  name = "Листопад";
			  break;
		  case 12:
			  name = "Грудень";
			  break;
		  default:
			  throw new EnergyproSystemException("Invalid number of month");
		  }

		  return name;
	  }


	/**
	 * Возвращает дату начала квартала для заданной даты
	 *
	 * @param dateIn - Дата
	 * @return Дата начала квартала для заданной даты
	 */
	public static Date getQuarterStartDate(Date dateIn)
	{
        Calendar cDateIn = Calendar.getInstance();
        cDateIn.setTime(dateIn);

        int month = cDateIn.get(Calendar.MONTH) + 1;

        int quarter;

        if (month <= 3)
        	quarter = 1;
        else if (month >= 4 && month <= 6)
        	quarter = 2;
        else if (month >= 7 && month <= 9)
        	quarter = 3;
        else
        	quarter = 4;


        Calendar cQuarter = Calendar.getInstance();
        cQuarter.setTime(new Date());
        cQuarter.set(Calendar.YEAR, cDateIn.get(Calendar.YEAR));
        cQuarter.set(Calendar.DATE, 1);

        switch (quarter)
        {
        case 1:
        	cQuarter.set(Calendar.MONTH, 0); // Январь
        	break;
        case 2:
        	cQuarter.set(Calendar.MONTH, 3); // Апрель
        	break;
        case 3:
        	cQuarter.set(Calendar.MONTH, 6); // Июль
        	break;
        case 4:
        	cQuarter.set(Calendar.MONTH, 9); // Октябрь
        	break;
        default:
        	throw new EnergyproSystemException("Invalid quarter");
        }

        Date tmpDate = cQuarter.getTime();
        cQuarter.setTime(clearTimeOfDate(tmpDate));

		return cQuarter.getTime();
	}



	  /**
	   *
	   * Возвращает дату, равную 1-му числу месяца заданной даты
	   *
	   * @param date - дата
	   * @return дата, равная 1-му числу месяца заданной даты
	   */
	  public static Date getFirstDayOfMonth(Date date)
	  {
		  Calendar calendar = Calendar.getInstance();

	      calendar.setTime(date);
	      calendar.set(Calendar.DAY_OF_MONTH, 1);

	      return clearTimeOfDate(calendar.getTime());
	  }


	  /**
	   *
	   * Возвращает дату, равную последнему числу месяца заданной даты
	   *
	   * @param date - дата
	   * @return дата, равная последнему числу месяца заданной даты
	   */
	  public static Date getLastDayOfMonth(Date date)
	  {
		  Calendar calendar = Calendar.getInstance();

	      calendar.setTime(date);
	      // Добавляем 1 месяц
	      calendar.add(Calendar.MONTH, 1);
	      // Переходим на 1-е число
	      calendar.set(Calendar.DAY_OF_MONTH, 1);
	      // Отнимаем 1 день
	      calendar.add(Calendar.DAY_OF_MONTH, -1);

	      return clearTimeOfDate(calendar.getTime());
	  }



	  /**
	   *
	   * Прибавляет к заданной дате нужное количество дней
	   *
	   * @param date - дата
	   * @param days - количество дней
	   * @return дата, равная исходной + количество дней
	   */
	  public static Date addDays(Date date, int days)
	  {
		  Calendar calendar = Calendar.getInstance();

	      calendar.setTime(date);
	      calendar.add(Calendar.DAY_OF_MONTH, days);

	      return calendar.getTime();
	  }

	  /**
	   *
	   * Прибавляет к заданной дате нужное количество часов
	   *
	   * @param date - дата
	   * @param hours - количество часов
	   * @return дата, равная исходной + количество часов
	   */
	  public static Date addHours(Date date, int hours) {
		  Calendar calendar = Calendar.getInstance();

	      calendar.setTime(date);
	      calendar.add(Calendar.HOUR_OF_DAY, hours);

	      return calendar.getTime();
	  }

	  public static Date  addMonth(Date date, int month)  {
	        Calendar cal = Calendar.getInstance();

	        cal.setTime(date);
	        cal.add(Calendar.MONTH, month);
	        return cal.getTime();
	  }

	  /**
	   * Возвращает дату завтрашнего дня
	   * @return дата завтрашнего дня
	   */
	  public static Date getTomorrowDate() {
		  Calendar calendar = Calendar.getInstance();
	      calendar.add(Calendar.DAY_OF_MONTH, 1);
	      return calendar.getTime();
	  }

	  /**
	   *
	   * Заменяет число (день) в заданной дате на указанное
	   *
	   * @param date - дата
	   * @param newDay - новая дата (т.е. число с 1 по 31)
	   * @return измененная дата
	   */
	  public static Date changeDay(Date date, int newDay)
	  {
		  // TODO: Проверки на допустимое значение для newDay?
		  Calendar calendar = Calendar.getInstance();

	      calendar.setTime(date);
	      calendar.set(Calendar.DAY_OF_MONTH, newDay);

	      return calendar.getTime();
	  }

	  public static XMLGregorianCalendar buildDate(int _year, int _month, int _day) throws DatatypeConfigurationException
	  {
		  return buildDate(_year, _month, _day, false);
	  }

	  /**
	   * Возвращает дату типа XMLGregorianCalendar (для Аксапты)
	   *
	   * @param _year - год
	   * @param _month - месяц
	   * @param _day - день
	   *
	   * @return дата типа XMLGregorianCalendar
	   *
	   * @throws DatatypeConfigurationException
	   */
	  public static XMLGregorianCalendar buildDate(int _year, int _month, int _day, boolean isZeroTime) throws DatatypeConfigurationException
	  {
	      //GregorianCalendar gCal;
		  XMLGregorianCalendar xGCal;

		  //gCal = new GregorianCalendar();
		  //gCal.set(2016, 06, 01);

		  if (isZeroTime) {
			  xGCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(
					  _year,
					  _month,
					  _day,
					  0, //DatatypeConstants.FIELD_UNDEFINED, // hour
					  0, //DatatypeConstants.FIELD_UNDEFINED, // minute
					  0, //DatatypeConstants.FIELD_UNDEFINED, // second
					  0, //DatatypeConstants.FIELD_UNDEFINED, // millisecond
					  DatatypeConstants.FIELD_UNDEFINED //timezone
				  );
		  } else {
			  xGCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(
					  _year,
					  _month,
					  _day,
					  DatatypeConstants.FIELD_UNDEFINED, // hour
					  DatatypeConstants.FIELD_UNDEFINED, // minute
					  DatatypeConstants.FIELD_UNDEFINED, // second
					  DatatypeConstants.FIELD_UNDEFINED, // millisecond
					  DatatypeConstants.FIELD_UNDEFINED //timezone
				  );
		  }

		  return xGCal;
	  }

	  public static XMLGregorianCalendar buildDate(Date date)
	  {
		  return buildDate(date, false);
	  }

	  /**
	   * Возвращает дату типа XMLGregorianCalendar (для Аксапты)
	   *
	   * @param date - дата (типа Date)
	   *
	   * @return дата типа XMLGregorianCalendar
	   */
	  public static XMLGregorianCalendar buildDate(Date date, boolean isZeroTime)
	  {
		  try {
			  int _year = getYear(date);
			  int _month = getMonth(date) + 1;
			  int _day = getDay(date);

			  return buildDate(_year, _month, _day, isZeroTime);
		  } catch (DatatypeConfigurationException e) {
			  throw new SystemException(e.getMessage(), e);
		  }
	  }


	/**
	 *
	 * Возвращает количество часов между двумя датами
	 *
	 * @param date1 начальная дата
	 * @param date2 конечная дата
	 * @return <b>BigDecimal</b> разница в часах между двумя датами
	 * @throws <b>IllegalArgumentException</b> если были посланы неправильные параметры
	 */
	public static BigDecimal getHoursBetweenTwoDates(Date date1, Date date2) throws IllegalArgumentException
	{
		BigDecimal out = new BigDecimal(0);

		/*Обязательно должны быть переданы даты*/
		if(date1 == null)
			throw new IllegalArgumentException("Параметру date1 было присвоено недопустимое значение null");
		if(date2 == null)
			throw new IllegalArgumentException("Параметру date2 было присвоено недопустимое значение null");

		/*Расчет разницы в миллисекундах*/
		long difference = date2.getTime() - date1.getTime();

		if(difference < 0)
			throw new IllegalArgumentException("Значение параметра date2 должно быть больше параметра date1");
		else
		{
			/*Расчет разницы часов*/
			BigDecimal milliseconds_in_hour = new BigDecimal(ENConsts.AMOUNT_OF_MINUTES_IN_HOURS * ENConsts.AMOUNT_OF_SECONDS_IN_MINUTE * ENConsts.AMOUNT_OF_MILLISECONDS_IN_SECOND);
			BigDecimal diff = new BigDecimal(difference);

			out = diff.divide(milliseconds_in_hour, 2, BigDecimal.ROUND_HALF_UP);
		}

		return out;
	}

	/**
	 *
	 * Возвращает количество дней между двумя датами
	 *
	 * @param date1 начальная дата
	 * @param date2 конечная дата
	 * @return <b>int</b> разница в днях между двумя датами
	 * @throws <b>IllegalArgumentException</b> если были переданы неправильные параметры
	 */
	public static int getDaysBetweenTwoDates(Date date1, Date date2) {
		if(date1 == null)
			throw new IllegalArgumentException("Параметру date1 было присвоено недопустимое значение null");
		if(date2 == null)
			throw new IllegalArgumentException("Параметру date2 было присвоено недопустимое значение null");

		long diffInMillies = date2.getTime() - date1.getTime();
		if(diffInMillies < 0)
			throw new IllegalArgumentException("Значение параметра date2 должно быть больше параметра date1");

		long hours = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);

		BigDecimal days = new BigDecimal(hours).divide(new BigDecimal(24), 0, BigDecimal.ROUND_HALF_UP);
		return days.intValue();
	}

	/**
	 *
	 * Возвращает строковое представление массива разделенное символом divider
	 *
	 * @param array массив
	 * @param divider символ-разделитель
	 * @return String
	 * @throws IllegalArgumentException при неправильно посланных параметрах
	 */
	public static String intArrayToStr(int[] array, String divider) {
		Integer[] intArr = new Integer[array.length];
		for(int i = 0; i < array.length; i++) intArr[i] = array[i];
		return Tools.intArrayToStr(intArr, divider);
	}

	/**
	 *
	 * Возвращает строковое представление массива разделенное символом divider
	 *
	 * @param array массив
	 * @param divider символ-разделитель
	 * @return String
	 * @throws IllegalArgumentException при неправильно посланных параметрах
	 */
	public static String intArrayToStr(Integer[] array, String divider) {
		return Tools.arrayToStr(array, divider);
	}

	public static <T> String arrayToStr(T[] array, String divider) {
		String out = "";
		if(array != null && array.length > 0) {
			for(T item : array) {
				out += ((out.length() > 0) ? divider : "") + item.toString();
			}
		}
		return out;
	}

	public String intArrayToStr1(int[] array, String divider) throws IllegalArgumentException
	{
		/*Обязательно должны быть переданы даты*/
		if(array == null)
			throw new IllegalArgumentException("Параметру array было присвоено недопустимое значение null");
		if(array.length == 0)
			throw new IllegalArgumentException("Количество 0 в array");

		String result = "";

		for(int i = 0; i < array.length; i++)
		{
			if(result.length() > 0)
				result = result + divider + array[i];
			else
				result = "" + array[i];
		}

		return result;

	}

	/**
	 *
	 * Функция проверка, что строка состоит только из цифер
	 *
	 * @param value строка
	 * @return true - если строка состоит только из цифр, false - если есть другие символы кроме цифр
	 * @throws IllegalArgumentException при неправильно посланных параметрах
	 */
	public static boolean checkIfStringConsistsOfDigits(String value) throws IllegalArgumentException
	{
		String check = "0123456789";
		if(value == null) throw new IllegalArgumentException("Параметру value было присвоено недопустимое значение null");
		if(value.length() > 0)
		{
			boolean result = true;
			int length = value.length();
			for(int i = 0; i < length; i++)
			{
				if(check.indexOf(value.substring(i, i+1)) == -1)
				{
					result = false;
					break;
				}
			}
			return result;
		}
		else
			return false;
	}

	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH);
	}

	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DATE);
	}

	/*
	 * по входящей дате вернет дату начала квартала
	 * */
	 public static Date getDateFirstDayOfQuarter(Date date)
	  {

		Calendar gcal = new GregorianCalendar();

		gcal.set(Calendar.YEAR, Tools.getYear(date));
	    gcal.set(Calendar.MONTH, Tools.getMonth(date)-1);
	    gcal.set(Calendar.DATE , Tools.getDay(date));
	    gcal.set(Calendar.HOUR_OF_DAY, 0);
	    gcal.set(Calendar.MINUTE, 0);
	    gcal.set(Calendar.SECOND, 0);
	    gcal.set(Calendar.MILLISECOND, 0);



	    /* Определение текущей даты, принимаемой за "сегодня". Относительно неё пойдет остальной расчет. */
	    SimpleDateFormat formattedDate = new SimpleDateFormat("dd.MM.yyyy");

	       /* Определение даты на начало текущего месяца */
	    gcal.set(Calendar.DAY_OF_MONTH, 1);



	    /* Определение даты на начало текущего квартала */
	       Integer monthDifference = 0;
	       if (gcal.get(Calendar.MONTH) == Calendar.JANUARY || gcal.get(Calendar.MONTH) == Calendar.APRIL ||
	               gcal.get(Calendar.MONTH) == Calendar.JULY || gcal.get(Calendar.MONTH) == Calendar.OCTOBER) {
	           monthDifference = 0;
	       } else if (gcal.get(Calendar.MONTH) == Calendar.FEBRUARY || gcal.get(Calendar.MONTH) == Calendar.MAY ||
	               gcal.get(Calendar.MONTH) == Calendar.AUGUST || gcal.get(Calendar.MONTH) == Calendar.NOVEMBER) {
	           monthDifference = -1;
	       } else if (gcal.get(Calendar.MONTH) == Calendar.MARCH || gcal.get(Calendar.MONTH) == Calendar.JUNE ||
	               gcal.get(Calendar.MONTH) == Calendar.SEPTEMBER || gcal.get(Calendar.MONTH) == Calendar.DECEMBER) {
	           monthDifference = -2;
	       }

	       gcal.add(Calendar.MONTH, monthDifference);
	       String dateFirstDayOfQuarter = formattedDate.format(gcal.getTime()); //Дата на начало текущего квартала
	       System.out.println("dateFirstDayOfQuarter = " + dateFirstDayOfQuarter);

		return gcal.getTime();

	  }

	 /**
	  *
	  * Последняя дата декады
	  *
	  * @param year год
	  * @param month месяц
	  * @param numberOfDecade номер декады в месяце
	  * @return последняя дата декады
	  */
	 public static Date getLastDateOfDecade(int year, int month, int numberOfDecade) {
		 Date out = null;
		 Calendar c = Calendar.getInstance();
		 c.setTime(new Date());
		 c.set(Calendar.MONTH, month - 1);
		 c.set(Calendar.YEAR, year);

		 switch(numberOfDecade) {
		 case 1:
			 c.set(Calendar.DAY_OF_MONTH, 10);
			 out = c.getTime();
			 break;
		 case 2:
			 c.set(Calendar.DAY_OF_MONTH, 20);
			 out = c.getTime();
			 break;
		 case 3:
			 c.set(Calendar.DAY_OF_MONTH, 1);
			 out = getLastDayOfMonth(c.getTime());
			 break;
			 default:
				 throw new java.lang.IllegalArgumentException("Неправильний номер декади");
		 }
		return out;

	 }

	 /**
	  *
	  * Выполнение jar-файла (и не только...)
	  *
	  * @param params параметры для вызова jar-файла (включая его имя, путь)
	  * @return Входящий поток от jar-файла
	  * @throws IOException
	  */
	 public static InputStream callJar(String[] params) throws IOException {
         ProcessBuilder pb = new ProcessBuilder(params);
         Process p = pb.start();
         InputStream inputStream = p.getInputStream();
         InputStream errorStream = p.getErrorStream();
         String errMessage = getStringFromInputStream(errorStream);
         if(!errMessage.isEmpty()) {
        	 throw new IOException(errMessage);
         }

         return inputStream;
	 }

	 /**
	  *
	  * Конвертирование входящего потока в строку
	  *
	  * @param inputStream входящий поток
	  * @return строка с сообщением из входящего потока
	  * @throws IOException
	  */
	 public static String getStringFromInputStream(InputStream inputStream) throws IOException {
		 String result = "";
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
         // errors
         while(true) {
             String line = bufferedReader.readLine();
             if(line == null) {
            	 bufferedReader.close();
                 break;
             }

             if(!result.isEmpty()) {
            	 result += "\n";
             }

              result = result + line;
           }
		 return result;
	 }

	 /**
	  *
	  * Создает объект CSVParser - парсера для csv-контента
	  *
	  * @param inputStream входящий поток с csv
	  * @param headers названия колонок в csv
	  * @param delimiter расделитель между столбцов
	  * @return CSVParser
	  * @throws IOException
	  */
	 public static CSVParser createSimpleCSVParser(InputStream inputStream, String[] headers, char delimiter) throws IOException {
		 return new CSVParser(new InputStreamReader(inputStream), CSVFormat.EXCEL.withDelimiter(delimiter).withHeader(headers).withSkipHeaderRecord());
	 }

	 /**
	  *
	  * Возвращает строковый формат даты в UTC формате
	  *
	  * @param _date Дата
	  * @return строковый формат даты в формате UTC
	  */
	 public static String getStrOfDateInUTC(Date _date) {
		 Calendar _calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		 _calendar.setTime(_date);
		 return String.format("(%d %d %d %d %d %d)",
				 new Object[] { Integer.valueOf(_calendar.get(Calendar.SECOND)),
				 Integer.valueOf(_calendar.get(Calendar.MINUTE)),
				 Integer.valueOf(_calendar.get(Calendar.HOUR_OF_DAY)),
				 Integer.valueOf(_calendar.get(Calendar.DAY_OF_MONTH)),
				 Integer.valueOf(_calendar.get(Calendar.MONTH) + 1),
				 Integer.valueOf(_calendar.get(Calendar.YEAR)) });
	 }



	public static String getInetAddress() {
		String ipAddres = "";
		try {

			InetAddress inetAddress = InetAddress.getLocalHost();
			ipAddres = inetAddress.getHostAddress();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ipAddres;
	}



	public static Date convertStringToDate(String dateString) {
		Date result = null;
		try {

			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

			synchronized (df) {

				result = df.parse(dateString);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}


	public static String convertDateToSQLString(Date date) {
		String result = "";

		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);

			result = "'" + calendar.get(Calendar.DAY_OF_MONTH) + "." + (calendar.get(Calendar.MONTH) + 1) + "."
					+ calendar.get(Calendar.YEAR) + "'";
		}

		return result;
	}


	/**
	 *
	 * Формирует новую строку где повторяет символ заданное число раз через указанный разделитель
	 *
	 * @param symbol символ
	 * @param divider разделитель между повторениями строк
	 * @param countTimes число раз для которых необходимо повторить
	 * @return сформированная строка
	 */
	public static String repeatSymbol(String symbol, String divider, int countTimes) {
		String out = "";
		if(countTimes > 0) {
			for(int i = 0; i < countTimes; i++) {
				out += (out.length() == 0) ? symbol : divider + symbol;
			}
		} else if(countTimes < 0) {
			throw new java.lang.IllegalArgumentException("must be greater than 0");
		}
		return out;
	}

	/**
	 *
	 * Возвращает строку с элементами коллекции разделенных между собой divider
	 *
	 * @param coll коллекция объектов
	 * @param divider разделитель между строками объектов
	 * @return строку с элементами коллекции разделенных между собой divider
	 */
	public static <Z> String collection2String(Collection<Z> coll, String divider) {
		String out = "";
		if(coll != null && coll.size() > 0) {
			for(Z item : coll) {
				out += ((out.length() > 0) ? divider : "") + item.toString();
			}
		}
		return out;
	}

	/**
	 *
	 * Возвращает массив строк с одинарными кавычками по бокам каждого элемента
	 *
	 * @param array заданный массив
	 * @return переконвертированный заданный массив в строковый с раставленными кавычками
	 */
	public static <Z> String[] getQuotedStringArray(Z[] array) {
		return transformArray(array, new Transformator<String, Z>() {
			@Override
			public String transform(Z value) {
				return (value == null) ? "''" : "'" + value.toString() + "'";
			}

		});
	}

	public static <Z> String[] getQuotedStringArray(Collection<Z> coll) {
		return transformToArray(coll, new Transformator<String, Z>() {
			@Override
			public String transform(Z value) {
				return (value == null) ? "''" : "'" + value.toString() + "'";
			}

		});
	}

	/**
	 *
	 * Возвращает вектор слов которые находятся в строке input
	 *
	 * @param input входящая строка
	 * @return вектор слов из входящей строки
	 */
	public static Vector<String> divideOnWords(String input, String delimiter) {
		if(input == null) throw new java.lang.NullPointerException("Input string can't be null");
		StringTokenizer tokenizer = new StringTokenizer(input);
		java.util.Vector<String> tokens = new java.util.Vector<>();
		while(tokenizer.hasMoreTokens()) {
			tokens.add(tokenizer.nextToken(delimiter));
		}
		return tokens;
	}

	/**
	 *
	 * Превращает коллекцию одних объектов в массив других обхъектов (можно и тех же самых) по заданному трансформатору (изменителю объектов)
	 *
	 * @param coll коллекция объектов
	 * @param transformator трансформатор
	 * @return массив новых объектов
	 */
	@SuppressWarnings("unchecked")
	public static <Z,T, L extends Collection<T>> Z[] transformToArray(L coll, Transformator<Z, T> transformator) {
		if(coll == null) return (Z[])new Object[0];
		Z[] array = null;
		int count = 0;
		for(T obj : coll) {
			Z tObj = transformator.transform(obj);
			if(array == null) {
				array = (Z[])Array.newInstance(tObj.getClass(), coll.size());
			}
			array[count++] = tObj;
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public static <Z,T> Z[] transformArray(T[] array, Transformator<Z, T> transformator) {
		if(array == null) return (Z[])new Object[0];
		Z[] arrayOut = (Z[])new Object[array.length];
		int count = 0;
		for(T obj : array) {
			arrayOut[count++] = transformator.transform(obj);
		}
		return arrayOut;
	}

	public static List<String> findAllMatches(String str, String regex) {
		if(str == null || regex == null) {
			throw new java.lang.NullPointerException("str and regex must be not null!!!");
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);

		List<String> list = new ArrayList<>();
		while(matcher.find()) {
			list.add(matcher.group(0));
		}

		return list;

	}

	/**
	 * Возвращает код валюты из AX по коду из ФК
	 */
	public static String getAXCurrencyCodeByFKCode(String fkCurrencyCode) {

		String axCurrencyCode;

		if (fkCurrencyCode.equals("980")) {

			axCurrencyCode = "UAH";

		} else if (fkCurrencyCode.equals("978")) {

			axCurrencyCode = "EUR";

		} else if (fkCurrencyCode.equals("810")) {

			axCurrencyCode = "RUB";

		} else if (fkCurrencyCode.equals("840")) {

			axCurrencyCode = "USD";

		} else {
			throw new SystemException("\n\nНевідомий код валюти: " + fkCurrencyCode);
		}

		return axCurrencyCode;

	}


	public static long getDaysDiff(Date date1, Date date2, TimeUnit timeUnit) {

		long diffInMillies = date2.getTime() - date1.getTime();

		return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}

	public static byte[] readBytesFromFile(String filePath) {

		FileInputStream fileInputStream = null;
		byte[] bytesArray = null;

		try {

			File file = new File(filePath);
			bytesArray = new byte[(int) file.length()];

			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bytesArray);
			file.delete();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		return bytesArray;
	}

	public static boolean checkValueInArray(int val, int[] arr)
	{
		for (int i = 0; i < arr.length; i++) {

			if (arr[i] == val) {

				return true;
			}

		}

		return false;
	}

	public static String transliterate(String str) {
		return transliterate(str, false, false);
	}

	public static String transliterate(String str, boolean caseSensitive) {
		return transliterate(str, caseSensitive, false);
	}

	public static String transliterate(String str, boolean caseSensitive, boolean isStrict) {
		return transliterate(str, caseSensitive, isStrict, false, "_");
	}

	/**
	 * Транслитерация строки
	 *
	 * @param str - исходная строка
	 * @param caseSensitive - если true, то регистр символов остается неизменным, если false - меняется на нижний
	 * @param isStrict - если true, то все символы, кроме букв и цифр, заменяются на replaceChar (по умолчанию "_")
	 * @param isUkr - если true, то транслитерируем с украинского
	 * @param replaceChar - символ, на который мы заменяем все левые символы
	 *
	 * @return Транслитерированная строка
	 */
	public static String transliterate(String str, boolean caseSensitive, boolean isStrict, boolean isUkr, String replaceChar) {
		String[] ru = { " ", "а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к", "л", "м", "н", "о", "п", "р",
				"с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ъ", "ы", "ь", "э", "ю", "я", "А", "Б", "В", "Г", "Д", "Е",
				"Ё", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ",
				"Ъ", "Ы", "Ь", "Э", "Ю", "Я", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
				"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
				"Ґ", "ґ", "Є", "є", "І", "і", "Ї", "ї"};
		String[] en = { " ", "a", "b", "v", "g", "d", "e", "e", "zh", "z", "i", "y", "k", "l", "m", "n", "o", "p", "r",
				"s", "t", "u", "f", "h", "ts", "ch", "sh", "sch", "", "i", "", "e", "ju", "ja", "A", "B", "V", "G", "D",
				"E", "E", "Zh", "Z", "I", "Y", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "F", "H", "Ts", "Ch",
				"Sh", "Sch", "", "I", "", "E", "Ju", "Ja", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
				"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G",
				"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
				"G", "g", "IE", "ie", "I", "i", "YI", "yi" };
		String[] ua_en = { " ", "a", "b", "v", "g", "d", "e", "e", "zh", "z", "y", "y", "k", "l", "m", "n", "o", "p", "r",
				"s", "t", "u", "f", "kh", "ts", "ch", "sh", "sch", "", "y", "", "e", "yu", "ya", "A", "B", "V", "G", "D",
				"E", "E", "Zh", "Z", "Y", "Y", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "F", "Kh", "Ts", "Ch",
				"Sh", "Sch", "", "Y", "", "E", "Yu", "Ya", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
				"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G",
				"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
				"G", "g", "Ie", "ie", "I", "i", "Yi", "yi" };
		;

		if (isUkr) {
			en = ua_en;
		}

        String outStr = "";
        boolean exist;
        for (int i = 0; i < str.length(); i++) {
            exist = false;

            for (int j = 0; j < ru.length; j++) {
            	if (isStrict && str.substring(i, i + 1).equals(" ")) {
            		break;
            	}

            	if (caseSensitive) {
	                if ((str.substring(i, i + 1).equals(ru[j]))) {
	                	outStr = outStr + en[j];
	                    exist = true;
	                    break;
	                }
            	} else {
	                if ((str.substring(i, i + 1).toLowerCase().equals(ru[j]))) {
	                	outStr = outStr + en[j];
	                    exist = true;
	                    break;
	                }
            	}
            }

            if (!exist) {
            	if (isStrict && !Character.isDigit(str.charAt(i)) && !(str.substring(i, i + 1).equals("."))) {
            		outStr = outStr + replaceChar;
            	} else {
            		outStr = outStr + str.substring(i, i + 1);
            	}
            }
        }

        return outStr;
    }

	/**
	 * Транслитерация строки из одного символа
	 *
	 * @param str - исходная строка (из одного символа)
	 *
	 * @return Транслитерированная строка (символ)
	 */
	public static String transliterateLetter(String str) {
		if (str == null || str.length() != 1) {
			throw new IllegalArgumentException("\n\nЦя функція використовується лише для строк із 1 символа!");
		}

		String[] ru = { " ", "а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к", "л", "м", "н", "о", "п", "р",
				"с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ъ", "ы", "ь", "э", "ю", "я", "А", "Б", "В", "Г", "Д", "Е",
				"Ё", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ",
				"Ъ", "Ы", "Ь", "Э", "Ю", "Я", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
				"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
				"Ґ", "ґ", "Є", "є", "І", "і", "Ї", "ї"};
		String[] en = { " ", "a", "b", "v", "g", "d", "e", "e", "j", "z", "y", "y", "k", "l", "m", "n", "o", "p", "r",
				"s", "t", "u", "f", "h", "c", "c", "s", "s", "", "i", "", "e", "y", "y", "A", "B", "V", "G", "D",
				"E", "E", "J", "Z", "Y", "Y", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "F", "H", "C", "C",
				"S", "S", "", "I", "", "E", "Y", "Y", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
				"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G",
				"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
				"G", "g", "E", "e", "I", "i", "Y", "y" };
		;

        String outStr = "";
        boolean exist = false;

        for (int j = 0; j < ru.length; j++) {
        	if (str.equals(" ")) {
        		break;
        	}

            if ((str.equals(ru[j]))) {
            	outStr = outStr + en[j];
                exist = true;
                break;
            }
        }

        if (!exist) {
        	if (!Character.isDigit(str.charAt(0)) && !(str.equals("."))) {
        		outStr = outStr + "_";
        	} else {
        		outStr = outStr + str;
        	}
        }

		if (outStr.length() != 1) {
			throw new SystemException("\n\ntransliterateLetter('" + str +"'): Помилка транслітерації (довжина результату = " + outStr.length() +
					")!\nРезультат: " + outStr);
		}

        return outStr;
    }

	@SuppressWarnings("rawtypes")
	public static javax.ejb.EJBHome createControllerHome(String jndiName, Class controllerClass)
	{
		try {
			Context context = new InitialContext();
	        Object ref = context.lookup(jndiName);

	        return (javax.ejb.EJBHome) PortableRemoteObject.narrow(ref, controllerClass);
		} catch (NamingException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        }
	}

	/**
	 *
	 * Проверяет на равенство объекты также работая с {@code null}-значениями
	 *
	 * @param a объект для сравнения 1
	 * @param b объект для сравнения 2
	 * @return {@code true} если равны {@code false} если не равны
	 */
	public static final boolean equals(Object a, Object b) {
		return (a == b) || (a != null && a.equals(b));
	}

	/**
	 * заполнение нулями слева
	 * @param stringToPad
	 * @param padToLength
	 * @return
	 */
	public static String PadLeftZero(String stringToPad, int padToLength){
        String retValue = null;
        if(stringToPad.length() < padToLength) {
            retValue = String.format("%0" + String.valueOf(padToLength - stringToPad.length()) + "d%s",0,stringToPad);
        }
        else{
            retValue = stringToPad;
        }
        return retValue;
    }

	public static String base64Encode(String inputString) {
		Base64.Encoder encoder = Base64.getEncoder();
		return new String(encoder.encode(inputString.getBytes()));
	}

	public static String base64Decode(String inputBase64String) {
		Base64.Decoder decoder = Base64.getDecoder();
		return new String(decoder.decode(inputBase64String));
	}

	public static Date localDateToDate(LocalDate date) {
		return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	public static LocalDate dateToLocalDate(Date date) {
		if(date == null) throw new NullPointerException("date is null!");
		return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
	}
	
	public static int countMonths(Date date1, Date date2) {
	    int comparing = date1.compareTo(date2); 
	    if(comparing == 0) return 0;
	    Date minDate = (comparing == 1) ? date2 : date1;
	    Date maxDate = (comparing == -1) ? date2 : date1;
	    Calendar maxCalendar = Calendar.getInstance();
	    Calendar minCalendar = Calendar.getInstance();
	    maxCalendar.setTime(maxDate);
	    minCalendar.setTime(minDate);
	    
	    int maxYear = maxCalendar.get(Calendar.YEAR), maxMonth = maxCalendar.get(Calendar.MONTH); 
	    int minYear = minCalendar.get(Calendar.YEAR), minMonth = minCalendar.get(Calendar.MONTH);
	     
	    return (maxYear - minYear) * 12 + maxMonth - minMonth; 
	    
	} 

	public static int[] concatIntArrays(int[] array1, int[] array2) {
	    return IntStream.concat(Arrays.stream(array1), Arrays.stream(array2)).toArray();
	}

}