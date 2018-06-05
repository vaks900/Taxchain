package com.ey.taxchain;

public class TransactionUtil {
    String connectionUrl="";
    public String getConnectionUrl(String investType){
        switch(investType){
            case "Mutual Fund":
                connectionUrl="http://10.168.3.251:10012/api/taxchain/O=Agent,L=Chennai,C=IN/O=StockBroker,L=Madurai,C=IN/create-pact";
                break;
            case "Insurance":
                connectionUrl="http://10.168.3.251:10015/api/taxchain/O=Agent,L=Chennai,C=IN/O=InsuranceCpy,L=Bangalore,C=IN/create-pact";
                break;
        }

        return connectionUrl;
    }
}
