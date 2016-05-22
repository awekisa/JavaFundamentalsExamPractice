package exam04_26_October_2015;

/**
 * Created by xxx on 4/18/2016.
 */
public class test
{
    public static void main(String[] args) {
        int i = 52;
        int adj = i%52;


        if (adj >=1 && adj <=26){
            System.out.println(adj);
            System.out.println((char)(adj + 96));
        }
        else if(adj >= 27 && adj <= 52){
            System.out.println(adj);
            System.out.println((char)(adj + 64 - 26));
        }
    }
}
