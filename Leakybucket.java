import java.util.Scanner;

public class Leakybucket
{
public static void main(String[] args)
{
Scanner sc= new Scanner(System.in);
System.out.println("Enter the bucket size=");
int n=sc.nextInt();
int a[]=new int[n];
System.out.println("Enter the number of packets=");
int num=sc.nextInt();

System.out.println("Enter the Data rate=");
for(int i=0;i<num;i++)
{
  a[i]=sc.nextInt();
}
System.out.println("Enter output rate=");
int out=sc.nextInt();

for(int i=0;i<num;i++)
if(a[i]>n)
{
  System.out.println("Bucket overflow\n"+a[i]);
}
else 
{
  if(a[i]==out)
    System.out.println("Packet transmited "+a[i]);
  else if(a[i]>out)
  {
    while(a[i]!=0 && a[i]>out)
    {
      System.out.println("Packet transmitted "+out);
      a[i]=a[i]-out;
    }
  System.out.println("Packet transmitted "+a[i]);
  }
}
}
}
