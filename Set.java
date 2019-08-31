package main;

import java.util.ArrayList;

public class Set {
	
	int dollars;

	
	private ArrayList<Double> probabs = new ArrayList<Double>();
	private ArrayList<Double> numOfToss = new ArrayList<Double>();

	Set(int d){
		dollars = d;
	}
	
	double return_prob(int i)
	{
		return probabs.get(i);
	}
	
	double returnNumOfToss(int i)
	{
		return numOfToss.get(i);
	}
	
	void addProbabsAndNumOfToss(double probab, double num)
	{
		probabs.add(probab);
		numOfToss.add(num);
	}
	
	int getLength()
	{
		return probabs.size();
	}
	
	
}
