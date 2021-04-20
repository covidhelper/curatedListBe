package org.covid19.helper.curatedList.Constants;

public class ApplicationConstants {

    public static final String SUCCESS = "success";

    public static String STATIC_BASE_FILE_PATH;

    public static String BASE_FILE_PATH = "/Users/rachitpuri/Documents/imt/";

//    public static String BASE_FILE_PATH;
//
//    @Value("${application.filepath}")
//    private String baseFilePath;
//
//    @Value("${application.filepath}")
//    public void setBaseFilePathStatic(String baseFilePath){
//        ApplicationConstants.BASE_FILE_PATH = baseFilePath;
//    }

    public static final String SAMARITAN = "samaritan";

    public static final String UUID = "uuid";
    public static final String PREVIEW = "preview";
    public static final String DATACARD = "dataCard";
    public static final String DATACARDS = "dataCards";
    public static final String DATACARDEVENT = "dataCardEvent";
    public static final String CITY = "city";

    public static enum RequestType {
        PLASMA,OXYGEN,ICU,VENTILATOR,BED,REMDESIVIR,DR,TIFFIN,HOME_CARE,TOCILIZUMAB,FABIFLU;
    }

    public static enum EventType{
        Unstructured,Structured,Verify,Seek;
    }

    public static enum DataCardAction{
        Verify,OutOfStock,Unanswered,Report;
    }


}


