package com.migu.schedule;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

/*
*类名和方法不能修改
 */
public class Schedule {
	List<TaskInfo> tak = null;
	Map<Integer,Integer> guaqimap = new HashMap<Integer,Integer>();
    public int init() {
    	tak = new ArrayList<TaskInfo>();
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
    	if(nodeId < 0)
    	{
    		return ReturnCodeKeys.E004;
    	}
		for(TaskInfo taskInfo:tak)
		{
			if(nodeId == taskInfo.getNodeId())
			{
				return ReturnCodeKeys.E005;
			}
		}
		TaskInfo taskInfo = new TaskInfo();
		taskInfo.setNodeId(nodeId);
		tak.add(taskInfo);
        return ReturnCodeKeys.E003;
    }

    public int unregisterNode(int nodeId) {
    	if(nodeId < 0)
    	{
    		return ReturnCodeKeys.E004;
    	}
    	for(TaskInfo taskInfo:tak)
		{
			if(nodeId == taskInfo.getNodeId())
			{
				tak.remove(nodeId);
				return ReturnCodeKeys.E006;
			}
		}
        return ReturnCodeKeys.E007;
    }


    public int addTask(int taskId, int consumption) {
    	if(taskId < 0)
    	{
    		return ReturnCodeKeys.E009;
    	}
    	for (Integer key : guaqimap.keySet()) {
    	      if(taskId == key)
    	      {
    	    	  return ReturnCodeKeys.E010;
    	      }
    	    }
    	guaqimap.put(taskId, consumption);
        return ReturnCodeKeys.E008;
    }


    public int deleteTask(int taskId) {
    	if(taskId < 0)
    	{
    		return ReturnCodeKeys.E009;
    	}
    	for(TaskInfo taskInfo:tak)
		{
			if(taskId == taskInfo.getTaskId())
			{
				taskInfo.setTaskId(0);
				tak.add(taskInfo);
				return ReturnCodeKeys.E011;
			}
		}
        return ReturnCodeKeys.E012;
    }


    public int scheduleTask(int threshold) {
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
    	if(null == tasks)
    	{
    		return ReturnCodeKeys.E016;
    	}
    	List<TaskInfo> tasks1 = new ArrayList<TaskInfo>();
    	for(TaskInfo taskInfo:tak)
    	{
    		for(TaskInfo taskInfo1:tasks)
    		{
    			if(taskInfo.getTaskId() == taskInfo1.getTaskId())
    			{
    				tasks1.add(taskInfo);
    			}
    		}
    	}
    	for (Integer key : guaqimap.keySet()) {
    		for(TaskInfo taskInfo1:tasks)
    		{
    			if(taskInfo1.getTaskId() == key)
    			{
    				taskInfo1.setNodeId(-1);
    				tasks1.add(taskInfo1);
    			}
    		}
  	    }
    	tasks.removeAll(tasks);
    	for(TaskInfo taskInfo1:tasks1)
    	{
    		tasks.add(taskInfo1);
    	}
        return ReturnCodeKeys.E015;
    }

}
