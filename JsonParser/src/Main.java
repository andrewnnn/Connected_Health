public class Main {

    public static void main(String[] args) {
        String input = "{\"status\":0,\"body\":{\"activities\":[{\"date\":\"2014-12-11\",\"steps\":66,\"distance\":94.99,\"calories\":4.03,\"elevation\":0,\"soft\":120,\"moderate\":0,\"intense\":0,\"timezone\":\"Australia\\/Adelaide\"},{\"date\":\"2014-12-12\",\"steps\":136,\"distance\":94.99,\"calories\":4.03,\"elevation\":0,\"soft\":120,\"moderate\":0,\"intense\":0,\"timezone\":\"Australia\\/Adelaide\"},{\"date\":\"2014-12-13\",\"steps\":2012,\"distance\":94.99,\"calories\":4.03,\"elevation\":0,\"soft\":120,\"moderate\":0,\"intense\":0,\"timezone\":\"Australia\\/Adelaide\"},{\"date\":\"2014-12-14\",\"steps\":799863,\"distance\":94.99,\"calories\":4.03,\"elevation\":0,\"soft\":120,\"moderate\":0,\"intense\":0,\"timezone\":\"Australia\\/Adelaide\"},{\"date\":\"2014-12-15\",\"steps\":0,\"distance\":94.99,\"calories\":4.03,\"elevation\":0,\"soft\":120,\"moderate\":0,\"intense\":0,\"timezone\":\"Australia\\/Adelaide\"}]}}";

        String output = getDataFromJson(input);

        System.out.println(input);
        System.out.println();
        System.out.println(output);
    }

    public static String getDataFromJson(String JsonInput) {
        String cleanInput = JsonInput.substring(JsonInput.indexOf("[{"), JsonInput.indexOf("}]") + 2);
        String[] splitInput = cleanInput.split("\\},\\{");
        String result = "[";
        for (int i = 0; i < splitInput.length; i++) {
            String Xtitle = "Date";
            String X = splitInput[i].substring(splitInput[i].indexOf("date\":\"") + 7,splitInput[i].indexOf("\",\"step"));
            String Ytitle = "Steps";
            String Y = splitInput[i].substring(splitInput[i].indexOf("steps\":") + 7,splitInput[i].indexOf(",\"distance"));
            result += ("{\"" + Xtitle + "\"=\"" + X + "\",\"" + Ytitle + "\"=" + Y + "}");
            if(i < (splitInput.length-1)){
                result += ",";
            }
        }
        result += "]";
        return result;
    }
}
