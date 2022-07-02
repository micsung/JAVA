import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintStream;

public class ReadabilityScore
{
    public static void main(String[] args)
    {
        File file;
        Scanner sc = null;
        String line;
        String line_1 ; 
        try
        {
            file = new File("../data/docset110.txt");//讀檔
            sc = new Scanner(file);
            sc.useDelimiter("[^A-Za-z]+");
            int words = 0 , sentence = 0 , characters = 0 ;
            
            while(sc.hasNext())//讀入每一行的文字
            {
                line = sc.next();//一次讀一行
                words ++;
                characters += line.length();
            }
            
            sc = new Scanner(file);
            while(sc.hasNext())
            {
                line = sc.next();
                int num = line.length();
                for(int i = 0 ; i < num ; i++)
                {
                    char x = line.charAt(i);
                    if (x =='.')
                    {

                        sentence ++ ;
                        /*if (line.charAt(i+1)==' ')
                        {
                            line = line.replaceAll(" ", "");
                            if (line.charAt(i+1)=='2')
                            {
                                sentence -- ;
                            }
                        }*/
                        if(line.charAt(i-1) >= 'A' && line.charAt(i-1) <= 'Z')
                        {
                            sentence -- ;
                        }
                        if(line.charAt(i-1) >= '0' && line.charAt(i-1) <= '9')
                        {
                            sentence -- ;
                        }
                    }
                }  
            }
                        

            sc.close();
            double a = words / sentence ;
            double b = characters / words ; 
            double GL = (0.37 * a) + (5.84 * b) - 26.01 ;
            int ans = (int)Math.round(GL);

            System.out.println(words);
            System.out.println(sentence);
            System.out.println(characters);
            System.out.printf("The Readability of the document is: GL%02d\n" , ans) ;

            PrintStream ps = new PrintStream("../data/result110.txt") ;//create file
            System.setOut(ps) ; //標準輸出流
            PrintStream out = System.out ; 
            System.out.printf("The Readability of the document is: GL%02d\n" , ans) ;

        }

        
        catch(FileNotFoundException e)//資料夾路徑錯誤
        {
            System.out.println("file not found");
        }
    }
}