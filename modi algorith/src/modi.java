import java.io.*;
class modi
{
static float a[][]=new float[10][10];
static  float b[][]=new float[10][10];
static float a1[][]=new float[10][10];
static float x[][]=new float[10][10];
static  float loop[][]=new float[10][10];
static float nbc[][]=new float[10][10];
static  float s[]=new float[10];
static float s1[]=new float[10];
static float d[]=new float[10];
static float d1[]=new float[10];
static float pi[]=new float[10];
static float pj[]=new float[10];
 static float ss=0,sd=0,min,min1,min2,maxp=0;
static char sign[][]=new char[10][10];
 static float u[]=new float[10];
static float v[]=new float[10];
static  int r,c,i,j,k,lc=0,mi,mj;
static float max=0,tc=0;
static  float flag=0,opt;

public static void main(String arg[])throws IOException
{
  input();
vam();
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
System.out.println("The Initial Basic Feasible Solution Using VAM");
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
displayallocmatrix();
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
k=0;
  for(i=1;i<=r;i++)
   for(j=1;j<=c;j++)
   {
     b[i][j]=a[i][j];
     if(x[i][j]!=0)
      k++;
   }
  for(i=0;i<r;i++)
   for(j=0;j<c;j++)
   {
     nbc[i][j]=0;
     loop[i][j]=0;
   }

  for(i=0;i<r;i++)
    u[i]=0;
  for(j=0;j<c;j++)
    v[j]=0;

  mi=0;mj=0;
  System.out.println("\nm+n-1 = "+(r+c-1));
  System.out.println("Allocations =  "+k+"\n");
  while(k==r+c-1)
  {
    /* FOR BASIC CELL */
    /* Counting the no.of allocations in row & column */
    for(i=1;i<=r;i++)
     for(j=1;j<=c;j++)
      if(x[i][j]!=0)
	u[i]++;
    for(j=1;j<=c;j++)
     for(i=1;i<=r;i++)
      if(x[i][j]!=0)
	v[j]++;

    /* Selecting the row or column having max no.of allocations */
    max=0;flag=0;
    for(i=1;i<=r;i++)
	 if(max<u[i])
	 {
       max=u[i];
       mi=i;
       flag=1;
     }
    for(j=1;j<=c;j++)
	 if(max<v[j])
	 {
       max=v[j];
       mj=j;
	   flag=2;
	 }
     for(i=1;i<=r;i++)
       u[i]=0;
     for(j=1;j<=c;j++)
       v[j]=0;

    /* Assigning value for u and v */
    if(flag==1) {
     for(j=1;j<=c;j++)
      if(x[mi][j]!=0)
	v[j]=b[mi][j];
	 for(k=1;k<=r;k++)
	 {
      for(i=1;i<=r;i++)
       for(j=1;j<=c;j++)
       {
    	   //System.out.println(x[i][j] + "&" + v[j] + "=" + b[i][j] + "-" + v[j]);
	if(x[i][j]!=0 && v[j]!=0)
	{
	 u[i]=b[i][j]-v[j];
	}
       }
	 for(j=1;j<=c;j++)
       for(i=1;i<=r;i++)
	if(x[i][j]!=0 && u[i]!=0)
	 v[j]=b[i][j]-u[i];
     }
    }

	if(flag==2)
	{
     for(i=1;i<=r;i++)
      if(x[i][mj]!=0)
	u[i]=b[i][mj];
	 for(k=1;k<=r;k++)
	 {
      for(j=1;j<=c;j++)
       for(i=1;i<=r;i++)
	if(x[i][j]!=0 && u[i]!=0)
	 v[j]=b[i][j]-u[i];
      for(i=1;i<=r;i++)
       for(j=1;j<=c;j++)
	if(x[i][j]!=0 && v[j]!=0)
	 u[i]=b[i][j]-v[j];
     }
    }

    /* FOR NON BASIC CELL */
    max=0;
    for(i=1;i<=r;i++)
     for(j=1;j<=c;j++)
	  if(x[i][j]==0)
	  {
       nbc[i][j]=b[i][j]-(u[i]+v[j]);
	   if(max>nbc[i][j])
	   {
	 max=nbc[i][j];
	 mi=i;
	 mj=j;
	   }
	  }
    if(max>=0)
      break;

    /* Loop Formation */
    for(i=1;i<=r;i++)
	 for(j=1;j<=c;j++)
	 {
      if(x[i][j]!=0)
	loop[i][j]=1;
      else
	loop[i][j]=0;
      sign[i][j]=' ';
     }

    for(k=1;k<=r;k++){
     for(i=1;i<=r;i++){
      for(j=1;j<=c;j++)
      if(loop[i][j]==1)
       lc++;
      if(lc==1 && i!=mi)
       for(j=1;j<=c;j++)
	loop[i][j]=0;
      lc=0;
     }

     lc=0;
     for(j=1;j<=c;j++){
      for(i=1;i<=r;i++)
       if(loop[i][j]==1)
	lc++;
      if(lc==1 && j!=mj)
       for(i=1;i<=r;i++)
	loop[i][j]=0;
      lc=0;
     }
    }

    /* Assigning the Sign */
    sign[mi][mj]='+';
    i=mi;
	for(k=1;k<=3;k++)
	{
     for(j=1;j<=c;j++)
	  if(loop[i][j]==1 && sign[i][j]==' ')
	  {
	sign[i][j]='-';
	break;
      }
     for(i=1;i<=r;i++)
	  if(loop[i][j]==1 && sign[i][j]==' ')
	  {
	sign[i][j]='+';
	break;
	  }
		}

    /* Finding @ Value */
    min=9999;
    for(i=1;i<=r;i++)
     for(j=1;j<=c;j++)
      if(sign[i][j]=='-' && min>x[i][j])
	min=x[i][j];
    for(i=1;i<=r;i++)
     for(j=1;j<=c;j++)
      if(sign[i][j]=='+')
	x[i][j]+=min;
      else if(sign[i][j]=='-')
	x[i][j]-=min;

    /* Checking m+n-1 Condition */
    k=0;
    for(i=1;i<=r;i++)
     for(j=1;j<=c;j++)
       if(x[i][j]!=0)
	k++;
  } /* End of While */

 
   System.out.println("The Optimum Solution Using Modi Method");
  System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
  displaytempmatrix();
  displayallocmatrix();
}

/* To get input values */
static void input()throws IOException
{
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
  System.out.print("\nEnter number of rows : ");
  r=Integer.parseInt(br.readLine());
  System.out.print("\nEnter number of columns : ");
  c=Integer.parseInt(br.readLine());
  System.out.println("\nDo you want to enter octogonal number(1) or general matrix(2) : ");
  int choice = Integer.parseInt(br.readLine());
  if (choice==2)
  {
  System.out.println("\nEnter the  Matrix Values(Enter row values first, press enter after every value)");
  for(i=1;i<=r;i++)
  {
   for(j=1;j<=c;j++)
   {
	a[i][j]=Integer.parseInt(br.readLine());
    a1[i][j]=b[i][j]=a[i][j];
	  }

    }
  System.out.print("\nEnter the Supply values: ");
  for(i=1;i<=r;i++)
  {
	s[i]=Integer.parseInt(br.readLine());
    s1[i]=s[i];
    ss+=s[i];
  }

   System.out.print("\nEnter the Demand Values: ");
   for(j=1;j<=c;j++)
   {
	d[j]=Integer.parseInt(br.readLine());
    d1[j]=d[j];
    sd+=d[j];
   }
  }
  if(choice==1)
  {
	  System.out.print("\nEnter the value of k: ");
	  float kval = Float.parseFloat(br.readLine());
	  for(i=1;i<=r;i++)
	  {
	   for(j=1;j<=c;j++)
	   {
		System.out.print("Enter the octogonal number for row-" + i + " column-" + j + " separated by comma like(1,2,3,4,5,6,7,8): ");
		String st = br.readLine();
		String oct[] = st.split(",");
		int xint[] = new int[9];
		for(int ij=0;ij<8;ij++)
			xint[ij+1]=Integer.parseInt(oct[ij]);
		a[i][j] = (float) (0.25*(kval*(xint[1]+xint[2]+xint[7]+xint[8])+(1-kval)*(xint[3]+xint[4]+xint[5]+xint[6])));
		//a[i][j]=Integer.parseInt(br.readLine());
	    a1[i][j]=b[i][j]=a[i][j];
		  }
	    }
	  System.out.print("\nEnter the Supply values: ");
	  for(i=1;i<=r;i++)
	  {
		System.out.print("Enter the octogonal number for supply value " + i +" separated by comma like(1,2,3,4,5,6,7,8): ");
		String st = br.readLine();
		String oct[] = st.split(",");
		int xint[] = new int[9];
		for(int ij=0;ij<8;ij++)
			xint[ij+1]=Integer.parseInt(oct[ij]);
		s[i] = (float) (0.25*(kval*(xint[1]+xint[2]+xint[7]+xint[8])+(1-kval)*(xint[3]+xint[4]+xint[5]+xint[6])));
	    s1[i]=s[i];
	    ss+=s[i];
	  }

	   System.out.print("\nEnter the Demand Values: ");
	   for(j=1;j<=c;j++)
	   {
		   System.out.print("Enter the octogonal number for demand value " + j +" separated by comma like(1,2,3,4,5,6,7,8): ");
		   String st = br.readLine();
			String oct[] = st.split(",");
			int xint[] = new int[9];
			for(int ij=0;ij<8;ij++)
				xint[ij+1]=Integer.parseInt(oct[ij]);
			d[j] = (float) (0.25*(kval*(xint[1]+xint[2]+xint[7]+xint[8])+(1-kval)*(xint[3]+xint[4]+xint[5]+xint[6])));
	    d1[j]=d[j];
	    sd+=d[j];
	   }
  }
  for(i=0;i<10;i++)
   for(j=0;j<10;j++)
    x[i][j]=0;

  

  /* Checking for Balanced */
 
  if(ss==sd)
  {
	System.out.println("\nThe demand and supply is balanced.\nThe matix looks like :");
  }
  if(ss<sd)
  {
    r++;
    s[r]=sd-ss;
    ss+=s[r];
    for(j=1;j<=c;j++)
      a[r][j]=0;
	System.out.println("\nThe demand and supply is unbalanced. It is balanced by adding one more row. The new matrix looks like: \n");
  }
  if(ss>sd)
  {
    c++;
    d[c]=ss-sd;
    sd+=d[c];
    for(i=1;i<=r;i++)
      a[i][c]=0;
    System.out.println("\nThe demand and supply is unbalanced. It is balanced by adding one more row. The new matrix looks like: \n");
  }
  
  displaymatrix();
 }

/* Function of Vogel's Approximation Method */

static void vam()
{
	System.out.println("Implementing VAM method for finding basic solution");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	
  for(i=1;i<=r;i++)
   for(j=1;j<=c;j++)
     b[i][j]=a[i][j];

  k=0;mi=0;mj=0;
  while(k<(r+c)-1)
  {

  /* selecting two min values in the row and subtracting*/
	for(i=1;i<=r;i++)
	{
     min1=9999;min2=9999;
     for(j=1;j<=c;j++)
	  if(min1>b[i][j] && b[i][j]!=-1)
	  {
	min1=b[i][j];
	mj=j;
      }
     for(j=1;j<=c;j++)
	  if(min2>b[i][j] && b[i][j]!=-1 && min2>=min1 && j!=mj)
	  min2=b[i][j];
	 pi[i]=min2-min1;
     if(pi[i]>9000)
       pi[i]=min1;
	}
  /* selecting two min values in the column and subtracting */
	for(j=1;j<=c;j++)
	{
     min1=9999;min2=9999;
     for(i=1;i<=r;i++)
	  if(min1>b[i][j] && b[i][j]!=-1)
	  {
	min1=b[i][j];
	mi=i;
      }
     for(i=1;i<=r;i++)
      if(min2>b[i][j] && b[i][j]!=-1 && min2>=min1 && i!=mi)
	min2=b[i][j];
     pj[j]=min2-min1;
     if(pj[j]>9000)
       pj[j]=min1;
    }
    /* finding the max value in the subtracted least values */
    maxp=0;flag=0;
    for(i=1;i<=r;i++)
	 if(maxp<pi[i])
	 {
       maxp=pi[i];
       mi=i;
       flag=1;
     }

    for(j=1;j<=c;j++)
	 if(maxp<pj[j])
	 {
       maxp=pj[j];
       mj=j;
       flag=2;
     }

     /* Selecting min value in max penalty row or column */
     min1=9999;
     if(flag==1)
       for(j=1;j<=c;j++)
	if(min1>b[mi][j] && b[mi][j]!=-1)
	{
	  min1=b[mi][j];
	  mj=j;
	}

     if(flag==2)
       for(i=1;i<=r;i++)
	if(min1>b[i][mj] && b[i][mj]!=-1)
	{
	  min1=b[i][mj];
	  mi=i;
	}
   /* Allocation */
    /*if demand is less */
	if(s[mi]>d[mj])
	{
      k++;
      x[mi][mj]=d[mj];
      s[mi]=s[mi]-d[mj];
      ss-=d[mj];
      sd-=d[mj];
      d[mj]=0;
      for(i=1;i<=r;i++)
	b[i][mj]=-1;
    }
	/* if supply is less */
	if(s[mi]<d[mj])
	{
      k++;
      x[mi][mj]=s[mi];
      d[mj]=d[mj]-s[mi];
      ss-=s[mi];
      sd-=s[mi];
      s[mi]=0;
      for(j=1;j<=c;j++)
	b[mi][j]=-1;
    }
	/*if supply and demand is equal */
	if(s[mi]==d[mj])
	{
      k++;
      x[mi][mj]=s[mi];
      ss-=s[mi];
      sd-=s[mi];
      s[mi]=0;
      d[mj]=0;
      for(i=1;i<=r;i++)
	b[i][mj]=-1;
      for(j=1;j<=c;j++)
	b[mi][j]=-1;
    }
	
	System.out.println("\nTemporary allocation matrix for allocation number: "+ k);
	displaytempmatrix();
    if((ss==0)&&(sd==0))
      break;    
    
  }
}

static void displaytempmatrix()
{
  for(i=1;i<=r;i++)
  {
   for(j=1;j<=c;j++)
	{
	System.out.print("	"+x[i][j]);
	}
   System.out.println(" | "+s[i]);
  }
  System.out.print("	");
  for(j=1;j<=c;j++)
		System.out.print("______"); 
  System.out.println("");
  for(j=1;j<=c;j++)
		System.out.print("	"+d[j]);  
  System.out.println();
}


static void displaymatrix()
{
  System.out.println("Cost Matrix");
  for(i=1;i<=r;i++)
  {
   for(j=1;j<=c;j++)
	{
	System.out.print("	"+a[i][j]);
	}
   System.out.println("	|	"+s[i]);
  }
  System.out.print("	");
  for(j=1;j<=c;j++)
		System.out.print("_______"); 
  System.out.println("");
  for(j=1;j<=c;j++)
		System.out.print("	"+d[j]);  
  
  System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
}

static void displayallocmatrix()
{
	 System.out.println("\nAllocated Matrix");
	 for(i=1;i<=r;i++)
	  {
	   for(j=1;j<=c;j++)
		{
		System.out.print("		"+x[i][j]+"*"+a[i][j]+"="+x[i][j]*a[i][j]);
		}
	   System.out.println("");
	  }	  
  tc=0;
  for(i=1;i<=r;i++)
    for(j=1;j<=c;j++)
	  tc=tc+(a[i][j]*x[i][j]);
  System.out.println("\nTotal Cost is "+tc+"\n\n");
  
  return;
}
}
			