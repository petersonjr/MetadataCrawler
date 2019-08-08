package io.github.petersonjr.metadatacrawler.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JdbcUrlParser {

	// Jdbcs Examples: https://www.ibm.com/support/knowledgecenter/en/SSEP7J_10.1.1/com.ibm.swg.ba.cognos.vvm_ag_guide.10.1.1.doc/c_ag_samjdcurlform.html
	// Based on: https://stackoverflow.com/a/40767001
	
	private static Pattern p = Pattern.compile("jdbc(:[\\w-]+){1,2}[\\/@:]+([^:\\/@]+)([:\\/]+|$)(\\d+)?.*$");
	
	/**
	 * Return 
	 * @param url
	 * @return Hostname from url or an empty string if we could not find it
	 */
	public static String getHost(String url) {
		Matcher m = p.matcher(url);
		if(m.matches()) {
			return(m.group(2));
		}
		
		return "";
	}
	
	/**
	 * 
	 * @param url
	 * @return port number from url or -1 if we could not find it
	 */
	public static int getPort(String url) {
		Matcher m = p.matcher(url);
		if(m.matches()) {
			if(m.group(4)!=null) {
				return Integer.valueOf(m.group(4));
			}
		}
		return -1;
	}

	public static void main(String[] args) {

		String[] urls = new String[] {
				"jdbc:cognos:dbapi@HOST:5555?domain=<DOMAIN>&dataSource=DATABASE_NAME",
				"jdbc:oracle:thin@HOST:5555:DATABASE_NAME",
				"jdbc:oracle:oci:@DATABASE_NAME",
				"jdbc:oracle:thin:@HOST:5555:<DATABASE>",
				"jdbc:oracle:thin:@HOST:5555:SID",
				"jdbc:oracle:thin:@//HOST:5555/SERVICE",

				"jdbc:db2:DATABASE_NAME",
				"jdbc:sybase:Tds:HOST:5555/DATABASE_NAME",

				"jdbc:db2://HOST:5555/DATABASE_NAME",
				"jdbc:db2://HOST:5555/DATABASE_NAME",
				"jdbc:informix-sqli://HOST:5555/DATABASE_NAME:informixserver=<IBM Informix instance name>;user=<user_name>;password=<password>",
				"jdbc:mysql://HOST:5555/DATABASE_NAME",
				"jdbc:netezza://HOST:5555/DATABASE_NAME",
				"jdbc:microsoft:sqlserver://HOST:5555;databaseName= DATABASE_NAME;SelectMethod=<SELECT_MODE>",
				"jdbc:teradata://HOST/DBS_PORT=5555/DATABASE=DATABASE_NAME/CHARSET=UTF8,COMPAT_DBS=true",
				"jdbc:jtds:sqlserver://ABC_XYZ:1433/Database;useBulkCopyForBatchInsert=false;cancelQueryTimeout=-1;sslProtocol=TLS;jaasConfigurationName=SQLJDBCDriver;statementPoolingCacheSize=0;serverPreparedStatementDiscardThreshold=10;enablePrepareOnFirstPreparedStatementCall=false;fips=false;socketTimeout=0;authentication=NotSpecified;authenticationScheme=nativeAuthentication;xopenStates=false;sendTimeAsDatetime=true;trustStoreType=JKS;trustServerCertificate=false;TransparentNetworkIPResolution=true;serverNameAsACE=false;sendStringParametersAsUnicode=true;selectMethod=direct;responseBuffering=adaptive;queryTimeout=-1;packetSize=8000;multiSubnetFailover=false;loginTimeout=15;lockTimeout=-1;lastUpdateCount=true;encrypt=false;disableStatementPooling=true;columnEncryptionSetting=Disabled;applicationName=Microsoft JDBC Driver for SQL Server;applicationIntent=readwrite;",
				"jdbc:sqlserver://host-name:1433;useBulkCopyForBatchInsert=false;cancelQueryTimeout=-1;sslProtocol=TLS;jaasConfigurationName=SQLJDBCDriver;statementPoolingCacheSize=0;serverPreparedStatementDiscardThreshold=10;enablePrepareOnFirstPreparedStatementCall=false;fips=false;socketTimeout=0;authentication=NotSpecified;authenticationScheme=nativeAuthentication;xopenStates=false;sendTimeAsDatetime=true;trustStoreType=JKS;trustServerCertificate=false;TransparentNetworkIPResolution=true;serverNameAsACE=false;sendStringParametersAsUnicode=true;selectMethod=direct;responseBuffering=adaptive;queryTimeout=-1;packetSize=8000;multiSubnetFailover=false;loginTimeout=15;lockTimeout=-1;lastUpdateCount=true;encrypt=false;disableStatementPooling=true;columnEncryptionSetting=Disabled;applicationName=Microsoft JDBC Driver for SQL Server;applicationIntent=readwrite;",
				"jdbc:oracle:thin:@host-name:1521:DATABASE"
			};
		
		for(String url : urls) {
			System.out.println(url);
			System.out.println(getHost(url));
			System.out.println(getPort(url));
		}
		
	}
}
