package capstone.zephyr.zephyr.model;

import java.util.ArrayList;

public class SetParametersRequest {
    private int pollID;
    private String parameter_1;
    private String parameter_2;
    private String parameter_3;
    private String parameter_4;
    private String parameter_5;
    private String parameter_6;
    private String parameter_7;
    private String parameter_8;
    private String parameter_9;
    private String parameter_10;
    private ArrayList<String> parameterNames;

    public SetParametersRequest(int pollID, String parameter_1, String parameter_2, String parameter_3, String parameter_4, String parameter_5, String parameter_6, String parameter_7, String parameter_8, String parameter_9, String parameter_10) {
        parameterNames = new ArrayList<String>();
        this.pollID = pollID;
        this.parameter_1 = parameter_1;
        this.parameter_2 = parameter_2;
        this.parameter_3 = parameter_3;
        this.parameter_4 = parameter_4;
        this.parameter_5 = parameter_5;
        this.parameter_6 = parameter_6;
        this.parameter_7 = parameter_7;
        this.parameter_8 = parameter_8;
        this.parameter_9 = parameter_9;
        this.parameter_10 = parameter_10;
        createParamterArray();
    }

    private void createParamterArray() {
        parameterNames.add(parameter_1);
        parameterNames.add(parameter_2);
        parameterNames.add(parameter_3);
        parameterNames.add(parameter_4);
        parameterNames.add(parameter_5);
        parameterNames.add(parameter_6);
        parameterNames.add(parameter_7);
        parameterNames.add(parameter_8);
        parameterNames.add(parameter_9);
        parameterNames.add(parameter_10);
    }

    public int getPollID() {
        return pollID;
    }

    public ArrayList<String> getParameterNames() {
        return parameterNames;
    }
}
