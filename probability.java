package main;

import java.math.*;
import java.util.Scanner;
import java.util.ArrayList;

public class probability {

	
	//ArrayList probabs
	//ArrayList numOfToss
	
	static ArrayList<Set> probabSet = new ArrayList<Set>();
	static ArrayList<Integer> powTwo = new ArrayList<Integer>();
	
	//문제점 발생 : 이건 동적 계획법이 아니야.
	//동적계획법은 top-down 방식이나 bottom-up 방식을 이용해야 하는데 전혀 그러고 있지 않잖아.
	//그리고 ArrayList에 n달러 이상 벌 set들이 추가되는 작업이 들어가야 해.
	static double minTrial(int n)
	{
		
		System.out.println("start for " + n);
		//k : n보다 큰 최소의 2^k의 지수
		//trial : 반환해줄 최소 시행 횟수
		int k=0;
		double binaryTemp = 1, trial = 0;
		
		//1. n보다 크거나 같은 최소의 2^k를 구할 수 있는 k를 구한다.
		//2. 동적계획법을 사용한다.
		//3. 초기값은 1이다.
		//4. 
		
		for(int i = 1; i < 100000; i++)
		{
			binaryTemp *= 2;
			if(binaryTemp >= (double)n)
				{
					k = i; break;
				}
		}
		//
		//System.out.println("k :  " + k);
		
		if(n == 1) 
		{
			probabSet.get(n).addProbabsAndNumOfToss(1, 1);
			return 1;
		}
		else
		{
			for(int i = k; i >= 0; i--)
			{
				//System.out.println("i :  " + i);
				double temp, powTwo;
				int temp2;
				if(i == k)
				{
					temp = Math.pow(0.5, k);
					trial += temp; 
					//for debugging
					//System.out.println("temp : " + temp + ", trial = " + trial );
					probabSet.get(n).addProbabsAndNumOfToss(temp, 1);
				}
				else
				{
					
					temp = Math.pow(0.5, i+1);
					
					//2^k-i승
					powTwo = Math.pow(2, i);
					temp2 = n-(int)powTwo;
					
					//System.out.println("here we go. :  " + i +", temp : " + temp + ", temp2 = " + temp2 );
					System.out.println("length of " +temp2 + "( " + n + "-"+ (int)powTwo + " ) : "+probabSet.get(temp2).getLength());
					
					
					for(int j = 0; j <probabSet.get(temp2).getLength(); j++)
					{
						//
						probabSet.get(n).addProbabsAndNumOfToss(
								temp * probabSet.get(temp2).return_prob(j)
								,probabSet.get(temp2).returnNumOfToss(j)+1 
								);
						trial += temp * probabSet.get(temp2).return_prob(j) *(probabSet.get(temp2).returnNumOfToss(j)+1 );
						
						//for debugging
						//System.out.println("temp : " + temp + ", trial = " + trial + ", temp2 = " + temp2 );
					}
					
				}
				
			}
		}
		
		return trial;
	}
	
	public static void main(String[] args){
		
		Scanner scan = new Scanner(System.in);
		probabSet = new ArrayList<Set>();
		powTwo.add(1);
		
		int n;
		while(true) {
		
			n = scan.nextInt();
			
			for(int i = 0; i <= n; i++)
			{
				probabSet.add(new Set(i));
				if(i>0) System.out.println(minTrial(i));
			}
			
		}
		
		
		//System.out.println(Math.pow(0.5,10) * Math.pow(2, 10) + " " + powTwo.get(0));
		
		
	}
}
