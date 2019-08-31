package main;

import java.math.*;
import java.util.Scanner;
import java.util.ArrayList;

public class myMain {

	//ArrayList probabs
		//ArrayList numOfToss
		
		static double[][] probab = new double[100][1100001];
		static double[][] numOfToss = new double[100][1100001];
		static int[] pivotProbab = new int[100];
		static int[] pivotNumOfToss = new int[100];
		
		
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
				probab[n][pivotProbab[n]] = 1;
				pivotProbab[n]++;
				numOfToss[n][pivotNumOfToss[n]] = 1;
				pivotNumOfToss[n]++;
				
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
						
						probab[n][pivotProbab[n]] = temp;
						pivotProbab[n]++;
						numOfToss[n][pivotNumOfToss[n]] = 1;
						pivotNumOfToss[n]++;
					}
					else
					{
						
						temp = Math.pow(0.5, i+1);
						
						//2^k-i승
						powTwo = Math.pow(2, i);
						temp2 = n-(int)powTwo;
						
						System.out.println("here we go. :  " + i +", temp : " + temp + ", temp2 = " + temp2 + ", pivotProbab[" + temp2 + "] = " + pivotProbab[temp2]  );
						//System.out.println("length of " +temp2 + " : "+probabSet.get(temp2).getLength());
								
						System.out.println(pivotProbab[n]);
						for(int j = 0; j <pivotProbab[temp2]; j++)
						{
							//System.out.println(j);
							//System.out.println(pivotProbab[n]);
							//System.out.println(pivotProbab[temp2]);
							//System.out.println(probab[temp2][j]);
							probab[n][pivotProbab[n]] = temp * probab[temp2][j];
							pivotProbab[n]++;
							numOfToss[n][pivotNumOfToss[n]] = numOfToss[temp2][j] +1;
							pivotNumOfToss[n]++;
							
							trial += temp *  probab[(int)powTwo][j] *(numOfToss[(int)powTwo][j] +1 );
							
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
			
			int n;
			
			n = scan.nextInt();
			
			for(int i = 0; i <= n; i++)
			{
				if(i>0) System.out.println(minTrial(i));
			}
			
			
//			for(int i = 0; i <100; i++)
//			{
//				for(int j = 0; j <90001; j++)
//				{
//					System.out.print(probab[i][j] + " ");
//				}
//				System.out.println("");
//			}
			
			
			//System.out.println(Math.pow(0.5,10) * Math.pow(2, 10) + " " + powTwo.get(0));
			
			
		}

}
