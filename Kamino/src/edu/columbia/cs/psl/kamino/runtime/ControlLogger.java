package edu.columbia.cs.psl.kamino.runtime;

import edu.columbia.cs.psl.kamino.Constants;

public class ControlLogger {

    public static void doTest(String whoDidtest) {
        System.out.println("You called doTest:" + whoDidtest);
    }

    public static void logEdgeControl(String className, String methodName, String methodDescriptor, int bbFrom, int bbTo) {
        System.out.println("LogEdgeControl:" + className + "." + methodName + methodDescriptor + "  From:" + bbFrom + " To:" + bbTo);
        Constants.logger.record("Control " + className + "." + methodName + methodDescriptor + " From:" + bbFrom + " To:" + bbTo + "\n");
    }

    public static void logEdgeControl(boolean branchTaken, String className, String methodName, String methodDescriptor, int bbFrom, int bbTaken,
            int bbNotTaken) {
        System.out.println("LogEdgeControl:" + className + "." + methodName + methodDescriptor + " From:" + bbFrom + " bbTaken:" + bbTaken
                + " bbNotTaken:" + bbNotTaken + " Taken:" + branchTaken);

        if (branchTaken) {
        	Constants.logger.record("Control " + className + "." + methodName + methodDescriptor + " From:" + bbFrom + " To:" + bbTaken + "\n");
        } else {
        	Constants.logger.record("Control " + className + "." + methodName + methodDescriptor + " From:" + bbFrom + " To:" + bbNotTaken + "\n");
        }
    }

    public static void logEdgeReadData(String className, String methodName, String methodDescriptor, int variableID, int frameID) {
        System.out.println("logEdgeReadData " + className + "." + methodName + methodDescriptor + "  variable_id:" + variableID + " Frame:" + frameID);
        Constants.logger.record("!!!!!!!!!!!!!!!!!!!!DataRead " + className + "." + methodName + methodDescriptor + " variable_id:" + variableID + " Frame:" + frameID + "\n");
    }

    public static void logEdgeWriteData(String className, String methodName, String methodDescriptor, int variableID, int frameID) {
        System.out.println("logEdgeWriteData " + className + "." + methodName + methodDescriptor + "  variable_id:" + variableID + " Frame:" + frameID);
        Constants.logger.record("DataWrite " + className + "." + methodName + methodDescriptor + " variable_id:" + variableID + " Frame:" + frameID + "\n");
    }
}
