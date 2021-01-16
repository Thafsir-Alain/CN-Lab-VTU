import java.util.Scanner;

public class Bellmanford
{
	int d[],noofvertices;
	public Bellmanford(int noofvertices) 
	{
		this.noofvertices = noofvertices;
		d = new int[noofvertices + 1];
	}
	
	public static void main(String[] args) 
	{
		int noofvertices,source;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the number of vertices:");
		noofvertices = s.nextInt();
		
		int A[][] = new int[noofvertices + 1][noofvertices + 1];
		System.out.println("Enter the adjacency matrix:");
		for(int sn = 1; sn <= noofvertices; sn++) {
			for(int dn = 1; dn <= noofvertices; dn++) {
				A[sn][dn] = s.nextInt();
				if(sn == dn) {
					A[sn][dn] = 0;
					continue;
				}
				if(A[sn][dn] == 0)
					A[sn][dn] = 999;
			}
		}
		
		System.out.println("Enter src vertex:");		
		source = s.nextInt();
		Bellmanford b = new Bellmanford(noofvertices);
		b.BellmanfordEvaluation(source,A);
	}
	
	private void BellmanfordEvaluation(int source, int[][] A) 
	{
		for(int node = 1; node <= noofvertices; node++)
			d[node] = 999;
		d[source] = 0;
		
		for(int node = 1; node <= noofvertices-1; node++) 
			for(int sn=1; sn <= noofvertices; sn++)
				for(int dn = 1;dn <= noofvertices; dn++)
					if(A[sn][dn] != 999)
						if(d[dn] > d[sn]+A[sn][dn])
							d[dn] = d[sn]+A[sn][dn];
		
		for(int sn = 1; sn <= noofvertices; sn++)
			for(int dn = 1; dn <= noofvertices; dn++)
				if(d[dn] > d[sn]+A[sn][dn])
					System.out.println("-ve cycle");
		
		for(int vertex = 1; vertex <= noofvertices; vertex++)
			System.out.println( source+" to "+ vertex +" is "+ d[vertex] );
	}
}
