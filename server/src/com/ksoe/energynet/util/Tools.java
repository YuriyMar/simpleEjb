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
	   * ������������� ����� ���� ������ 00:00:00
	   *
	   * @param date ���� � ������������� ��������
	   * @return ���� ��� �������
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
	   * ���������� ���� �� �������� ����, ������ � ��� (����� = 00:00:00)
	   *
	   * @param year - ���
	   * @param month - ����� (� ������������ ������� �� 1 �� 12)
	   * @param day - ���� (�� 1 �� 31)
	   *
	   * @return ����, � ������� ��� = year, ����� = month, ���� = day � ����� = 00:00:00
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
	   * ���������� ���� ������ ������
	   *
	   * @param year - ���
	   * @param month - ����� (� ������������ ������� �� 1 �� 12)
	   * @param decadeNumber - ����� ������ (1, 2 ��� 3)
	   *
	   * @return ���� ������ ������
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
				  throw new EnergyproSystemException("\n\n������������ ����� ������: " + decadeNumber);
		  }

		  return encodeDate(year, month, day);
	  }

	  /**
	   * ���������� ���� ��������� ������
	   *
	   * @param year - ���
	   * @param month - ����� (� ������������ ������� �� 1 �� 12)
	   * @param decadeNumber - ����� ������ (1, 2 ��� 3)
	   *
	   * @return ���� ��������� ������
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
				  throw new EnergyproSystemException("\n\n������������ ����� ������: " + decadeNumber);
		  }

		  return encodeDate(year, month, day);
	  }

	  /**
	   *
	   * ���������� ���������� ������������ ������ �� ��� ������ � ����
	   *
	   * @param monthNumber ����� ������
	   * @return String ���������� ������������ ������
	   */
	  public static String getUkrNameOfMonth(int monthNumber)
	  {
		  String name = "";
		  switch(monthNumber)
		  {
		  case 1:
			  name = "ѳ����";
			  break;
		  case 2:
			  name = "�����";
			  break;
		  case 3:
			  name = "��������";
			  break;
		  case 4:
			  name = "������";
			  break;
		  case 5:
			  name = "�������";
			  break;
		  case 6:
			  name = "�������";
			  break;
		  case 7:
			  name = "������";
			  break;
		  case 8:
			  name = "�������";
			  break;
		  case 9:
			  name = "��������";
			  break;
		  case 10:
			  name = "�������";
			  break;
		  case 11:
			  name = "��������";
			  break;
		  case 12:
			  name = "�������";
			  break;
		  default:
			  throw new EnergyproSystemException("Invalid number of month");
		  }

		  return name;
	  }


	/**
	 * ���������� ���� ������ �������� ��� �������� ����
	 *
	 * @param dateIn - ����
	 * @return ���� ������ �������� ��� �������� ����
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
        	cQuarter.set(Calendar.MONTH, 0); // ������
        	break;
        case 2:
        	cQuarter.set(Calendar.MONTH, 3); // ������
        	break;
        case 3:
        	cQuarter.set(Calendar.MONTH, 6); // ����
        	break;
        case 4:
        	cQuarter.set(Calendar.MONTH, 9); // �������
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
	   * ���������� ����, ������ 1-�� ����� ������ �������� ����
	   *
	   * @param date - ����
	   * @return ����, ������ 1-�� ����� ������ �������� ����
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
	   * ���������� ����, ������ ���������� ����� ������ �������� ����
	   *
	   * @param date - ����
	   * @return ����, ������ ���������� ����� ������ �������� ����
	   */
	  public static Date getLastDayOfMonth(Date date)
	  {
		  Calendar calendar = Calendar.getInstance();

	      calendar.setTime(date);
	      // ��������� 1 �����
	      calendar.add(Calendar.MONTH, 1);
	      // ��������� �� 1-� �����
	      calendar.set(Calendar.DAY_OF_MONTH, 1);
	      // �������� 1 ����
	      calendar.add(Calendar.DAY_OF_MONTH, -1);

	      return clearTimeOfDate(calendar.getTime());
	  }



	  /**
	   *
	   * ���������� � �������� ���� ������ ���������� ����
	   *
	   * @param date - ����
	   * @param days - ���������� ����
	   * @return ����, ������ �������� + ���������� ����
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
	   * ���������� � �������� ���� ������ ���������� �����
	   *
	   * @param date - ����
	   * @param hours - ���������� �����
	   * @return ����, ������ �������� + ���������� �����
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
	   * ���������� ���� ����������� ���
	   * @return ���� ����������� ���
	   */
	  public static Date getTomorrowDate() {
		  Calendar calendar = Calendar.getInstance();
	      calendar.add(Calendar.DAY_OF_MONTH, 1);
	      return calendar.getTime();
	  }

	  /**
	   *
	   * �������� ����� (����) � �������� ���� �� ���������
	   *
	   * @param date - ����
	   * @param newDay - ����� ���� (�.�. ����� � 1 �� 31)
	   * @return ���������� ����
	   */
	  public static Date changeDay(Date date, int newDay)
	  {
		  // TODO: �������� �� ���������� �������� ��� newDay?
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
	   * ���������� ���� ���� XMLGregorianCalendar (��� �������)
	   *
	   * @param _year - ���
	   * @param _month - �����
	   * @param _day - ����
	   *
	   * @return ���� ���� XMLGregorianCalendar
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
	   * ���������� ���� ���� XMLGregorianCalendar (��� �������)
	   *
	   * @param date - ���� (���� Date)
	   *
	   * @return ���� ���� XMLGregorianCalendar
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
	 * ���������� ���������� ����� ����� ����� ������
	 *
	 * @param date1 ��������� ����
	 * @param date2 �������� ����
	 * @return <b>BigDecimal</b> ������� � ����� ����� ����� ������
	 * @throws <b>IllegalArgumentException</b> ���� ���� ������� ������������ ���������
	 */
	public static BigDecimal getHoursBetweenTwoDates(Date date1, Date date2) throws IllegalArgumentException
	{
		BigDecimal out = new BigDecimal(0);

		/*����������� ������ ���� �������� ����*/
		if(date1 == null)
			throw new IllegalArgumentException("��������� date1 ���� ��������� ������������ �������� null");
		if(date2 == null)
			throw new IllegalArgumentException("��������� date2 ���� ��������� ������������ �������� null");

		/*������ ������� � �������������*/
		long difference = date2.getTime() - date1.getTime();

		if(difference < 0)
			throw new IllegalArgumentException("�������� ��������� date2 ������ ���� ������ ��������� date1");
		else
		{
			/*������ ������� �����*/
			BigDecimal milliseconds_in_hour = new BigDecimal(ENConsts.AMOUNT_OF_MINUTES_IN_HOURS * ENConsts.AMOUNT_OF_SECONDS_IN_MINUTE * ENConsts.AMOUNT_OF_MILLISECONDS_IN_SECOND);
			BigDecimal diff = new BigDecimal(difference);

			out = diff.divide(milliseconds_in_hour, 2, BigDecimal.ROUND_HALF_UP);
		}

		return out;
	}

	/**
	 *
	 * ���������� ���������� ���� ����� ����� ������
	 *
	 * @param date1 ��������� ����
	 * @param date2 �������� ����
	 * @return <b>int</b> ������� � ���� ����� ����� ������
	 * @throws <b>IllegalArgumentException</b> ���� ���� �������� ������������ ���������
	 */
	public static int getDaysBetweenTwoDates(Date date1, Date date2) {
		if(date1 == null)
			throw new IllegalArgumentException("��������� date1 ���� ��������� ������������ �������� null");
		if(date2 == null)
			throw new IllegalArgumentException("��������� date2 ���� ��������� ������������ �������� null");

		long diffInMillies = date2.getTime() - date1.getTime();
		if(diffInMillies < 0)
			throw new IllegalArgumentException("�������� ��������� date2 ������ ���� ������ ��������� date1");

		long hours = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);

		BigDecimal days = new BigDecimal(hours).divide(new BigDecimal(24), 0, BigDecimal.ROUND_HALF_UP);
		return days.intValue();
	}

	/**
	 *
	 * ���������� ��������� ������������� ������� ����������� �������� divider
	 *
	 * @param array ������
	 * @param divider ������-�����������
	 * @return String
	 * @throws IllegalArgumentException ��� ����������� ��������� ����������
	 */
	public static String intArrayToStr(int[] array, String divider) {
		Integer[] intArr = new Integer[array.length];
		for(int i = 0; i < array.length; i++) intArr[i] = array[i];
		return Tools.intArrayToStr(intArr, divider);
	}

	/**
	 *
	 * ���������� ��������� ������������� ������� ����������� �������� divider
	 *
	 * @param array ������
	 * @param divider ������-�����������
	 * @return String
	 * @throws IllegalArgumentException ��� ����������� ��������� ����������
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
		/*����������� ������ ���� �������� ����*/
		if(array == null)
			throw new IllegalArgumentException("��������� array ���� ��������� ������������ �������� null");
		if(array.length == 0)
			throw new IllegalArgumentException("���������� 0 � array");

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
	 * ������� ��������, ��� ������ ������� ������ �� �����
	 *
	 * @param value ������
	 * @return true - ���� ������ ������� ������ �� ����, false - ���� ���� ������ ������� ����� ����
	 * @throws IllegalArgumentException ��� ����������� ��������� ����������
	 */
	public static boolean checkIfStringConsistsOfDigits(String value) throws IllegalArgumentException
	{
		String check = "0123456789";
		if(value == null) throw new IllegalArgumentException("��������� value ���� ��������� ������������ �������� null");
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
	 * �� �������� ���� ������ ���� ������ ��������
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



	    /* ����������� ������� ����, ����������� �� "�������". ������������ �� ������ ��������� ������. */
	    SimpleDateFormat formattedDate = new SimpleDateFormat("dd.MM.yyyy");

	       /* ����������� ���� �� ������ �������� ������ */
	    gcal.set(Calendar.DAY_OF_MONTH, 1);



	    /* ����������� ���� �� ������ �������� �������� */
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
	       String dateFirstDayOfQuarter = formattedDate.format(gcal.getTime()); //���� �� ������ �������� ��������
	       System.out.println("dateFirstDayOfQuarter = " + dateFirstDayOfQuarter);

		return gcal.getTime();

	  }

	 /**
	  *
	  * ��������� ���� ������
	  *
	  * @param year ���
	  * @param month �����
	  * @param numberOfDecade ����� ������ � ������
	  * @return ��������� ���� ������
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
				 throw new java.lang.IllegalArgumentException("������������ ����� ������");
		 }
		return out;

	 }

	 /**
	  *
	  * ���������� jar-����� (� �� ������...)
	  *
	  * @param params ��������� ��� ������ jar-����� (������� ��� ���, ����)
	  * @return �������� ����� �� jar-�����
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
	  * ��������������� ��������� ������ � ������
	  *
	  * @param inputStream �������� �����
	  * @return ������ � ���������� �� ��������� ������
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
	  * ������� ������ CSVParser - ������� ��� csv-��������
	  *
	  * @param inputStream �������� ����� � csv
	  * @param headers �������� ������� � csv
	  * @param delimiter ����������� ����� ��������
	  * @return CSVParser
	  * @throws IOException
	  */
	 public static CSVParser createSimpleCSVParser(InputStream inputStream, String[] headers, char delimiter) throws IOException {
		 return new CSVParser(new InputStreamReader(inputStream), CSVFormat.EXCEL.withDelimiter(delimiter).withHeader(headers).withSkipHeaderRecord());
	 }

	 /**
	  *
	  * ���������� ��������� ������ ���� � UTC �������
	  *
	  * @param _date ����
	  * @return ��������� ������ ���� � ������� UTC
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
	 * ��������� ����� ������ ��� ��������� ������ �������� ����� ��� ����� ��������� �����������
	 *
	 * @param symbol ������
	 * @param divider ����������� ����� ������������ �����
	 * @param countTimes ����� ��� ��� ������� ���������� ���������
	 * @return �������������� ������
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
	 * ���������� ������ � ���������� ��������� ����������� ����� ����� divider
	 *
	 * @param coll ��������� ��������
	 * @param divider ����������� ����� �������� ��������
	 * @return ������ � ���������� ��������� ����������� ����� ����� divider
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
	 * ���������� ������ ����� � ���������� ��������� �� ����� ������� ��������
	 *
	 * @param array �������� ������
	 * @return �������������������� �������� ������ � ��������� � ������������� ���������
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
	 * ���������� ������ ���� ������� ��������� � ������ input
	 *
	 * @param input �������� ������
	 * @return ������ ���� �� �������� ������
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
	 * ���������� ��������� ����� �������� � ������ ������ ��������� (����� � ��� �� �����) �� ��������� �������������� (���������� ��������)
	 *
	 * @param coll ��������� ��������
	 * @param transformator �������������
	 * @return ������ ����� ��������
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
	 * ���������� ��� ������ �� AX �� ���� �� ��
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
			throw new SystemException("\n\n�������� ��� ������: " + fkCurrencyCode);
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
	 * �������������� ������
	 *
	 * @param str - �������� ������
	 * @param caseSensitive - ���� true, �� ������� �������� �������� ����������, ���� false - �������� �� ������
	 * @param isStrict - ���� true, �� ��� �������, ����� ���� � ����, ���������� �� replaceChar (�� ��������� "_")
	 * @param isUkr - ���� true, �� ��������������� � �����������
	 * @param replaceChar - ������, �� ������� �� �������� ��� ����� �������
	 *
	 * @return ������������������� ������
	 */
	public static String transliterate(String str, boolean caseSensitive, boolean isStrict, boolean isUkr, String replaceChar) {
		String[] ru = { " ", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
				"�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
				"�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
				"�", "�", "�", "�", "�", "�", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
				"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
				"�", "�", "�", "�", "�", "�", "�", "�"};
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
	 * �������������� ������ �� ������ �������
	 *
	 * @param str - �������� ������ (�� ������ �������)
	 *
	 * @return ������������������� ������ (������)
	 */
	public static String transliterateLetter(String str) {
		if (str == null || str.length() != 1) {
			throw new IllegalArgumentException("\n\n�� ������� ��������������� ���� ��� ����� �� 1 �������!");
		}

		String[] ru = { " ", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
				"�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
				"�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
				"�", "�", "�", "�", "�", "�", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
				"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
				"�", "�", "�", "�", "�", "�", "�", "�"};
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
			throw new SystemException("\n\ntransliterateLetter('" + str +"'): ������� ������������� (������� ���������� = " + outStr.length() +
					")!\n���������: " + outStr);
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
	 * ��������� �� ��������� ������� ����� ������� � {@code null}-����������
	 *
	 * @param a ������ ��� ��������� 1
	 * @param b ������ ��� ��������� 2
	 * @return {@code true} ���� ����� {@code false} ���� �� �����
	 */
	public static final boolean equals(Object a, Object b) {
		return (a == b) || (a != null && a.equals(b));
	}

	/**
	 * ���������� ������ �����
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