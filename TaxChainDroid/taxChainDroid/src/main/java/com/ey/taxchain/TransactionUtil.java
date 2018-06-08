package com.ey.taxchain;

public class TransactionUtil {
    String connectionUrl="";
    public String getConnectionUrl(String investType){
        switch(investType){
            case "Mutual Fund":
                connectionUrl="http://169.254.168.157:10012/api/taxchain/O=Agent,L=Chennai,C=IN/O=StockBroker,L=Madurai,C=IN/create-pact";
                break;
            case "Insurance":
                connectionUrl="http://169.254.168.157:10015/api/taxchain/O=Agent,L=Chennai,C=IN/O=InsuranceCpy,L=Bangalore,C=IN/create-pact";
                break;
        }

        return connectionUrl;
    }

    public static String convertStandardJSONString(String data_json) {
        data_json = data_json.replaceAll("\\\\", "");
        data_json = data_json.replace("\"{", "{");
        data_json = data_json.replace("}\",", "},");
        data_json = data_json.replace("}\"", "}");
        return data_json;
    }
}
