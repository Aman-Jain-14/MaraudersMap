#include <stdio.h>

int main()
{
	int arr[5] = {5,4,3,2,1};
	int temp,i,j,n=5,pos,min,c;

	for(i=0 ; i< n ; i++)
	{
		min = arr[i];
		c = 0;
		for(j=i+1 ; j<n ; j++)
		{
			if(min > arr[j])
			{
			 	min = arr[j];
			 	pos=j;
			 	c=1;
			}
		}

		if(c==1)
		{
			temp = arr[i];
			arr[i] = arr[pos] ; 
			arr[pos] = temp;
		}
			
	}
	printf("\n");
	for(i=0 ; i<n ; i++)
		printf("%d " ,arr[i] );
}