//Customised Virtual DataBase Management System
//Date 01-08-2022
//with the Location 

import java.lang.*;
import java.util.*;

import javax.sql.rowset.spi.SyncResolver;

//main Class
class CVDBMS
{
    public static void main(String arg[])
    {
        DBMS dobj=new DBMS();

        dobj.StartDBMS();
    }
}

//Create table student (RID int ,Name varchar(255),Salary int)
//DataBase Table/Schema
class Student
{
    public int RID;
    public String Name;
    public int Salary;
    public String Location;

    private static int Generator;  //can also initialize here by zero
    static
    {
        Generator=0;
    }

    public Student(String str,int value,String location)
    {
        this.RID=++Generator;
        this.Name=str;
        this.Salary=value;
        this.Location=location;
    }

    public void Displaydata()
    {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("|\t"+this.RID+"\t|\t"+this.Name+"\t|\t"+this.Salary+"\t|\t"+this.Location+"\t|");
        //System.out.println("-------------------------------------------------");
    }
}

class DBMS
{
    public LinkedList<Student> lobj;          //<> Generic LinkedList
    public DBMS()
    {
        lobj=new LinkedList<>(); //to allocate the resource in the constructor.
    }

    public void StartDBMS()
    {
        Scanner scanobj=new Scanner(System.in);

        System.out.println("Marvellous customised DBMS started Successfully");
        String OriginalQuery="";
         

        while(true)
        {
            System.out.println("Marvellous DBMS Console");
            OriginalQuery =scanobj.nextLine();

            String Query=OriginalQuery.toLowerCase();

            String tokens[]=Query.split(" ");
            int QuerySize=tokens.length;

            if(QuerySize==1)
            {
                if("help".equals(tokens[0]))
                {
                    System.out.println("This Application used to demonstrate the customised DBMS");
                    System.out.println("1.Help                     : For query syntax");
                    System.out.println("2.Exit                     : Terminate DBMS");
                    System.out.println("3.Display all Data         : Select * from student");
                    System.out.println("4.Insert Data              : Insert into student Name Salary Location");
                    System.out.println("5.Max Data                 : Select Max Salary from student");
                    System.out.println("6.Min Data                 : Select Min Salary from student");
                    System.out.println("7.Sum Data                 : Select sum Salary from student");
                    System.out.println("8.Avg Data                 : Select average salary from student");
                    System.out.println("9.Count Data               : Select count id from student");
                    System.out.println("10.Delete by RID           : Delete from student where rid =  ...");
                    System.out.println("11.Delete by Name          : Delete from student where name = ...");
                    System.out.println("12.Display Specific by Name: select * from student where name = ...");
                    System.out.println("13.Display Specific by RID : select * from student where rid = ...");
                    System.out.println("14.Update Name             : update student set name = new_name where name = old_name");
                    System.out.println("15.Update Salary           : update student set salary = new_salary where salary = old_salary");
                    System.out.println("16.Salary Greater than     : select * from student where salary > ...");
                    System.out.println("17.Salary Less than        : select * from student where salary <...");

                }
                else if("exit".equals(tokens[0]))
                {
                    System.out.println("Thank you for using Marvellous Customized DBMS");
                    break;
                }
            }
            else if(QuerySize==2)
            {

            }
            else if(QuerySize==4)
            {
                if("select".equals(tokens[0]))
                {
                    if("*".equals(tokens[1]))
                    {
                        DisplayAll();
                    }
                }
            }

            //Insert into student piyush 5000
            else if(QuerySize==5)
            {
                if("min".equals(tokens[1]))
                {
                    AggregateMin();
                }
                else if("max".equals(tokens[1]))
                {
                   AggregateMax();
                }
                else if("sum".equals(tokens[1]))
                {
                    AggregateSum();
                }
                else if("avg".equals(tokens[1]))
                {
                    AggregateAvg();
                }
                else if("count".equals(tokens[1]));
                {
                    AggregateCount();
                }

            }
            else if(QuerySize==6)
            {
                if("insert".equals(tokens[0]))
                {
                    Insertdata(tokens[3],Integer.parseInt(tokens[4]),tokens[5]);
                }
            }

            else if(QuerySize==7)
            {
                if("delete".equals(tokens[0]))
                {
                    if("rid".equals(tokens[4]))
                    {
                        String s=tokens[6];
                        int si=Integer.parseInt(s);
                        DeleteSpecific(si);
                    }
                    else if("name".equals(tokens[4]))
                    {
                        DeleteSpecific(tokens[6]);
                    }
                }
            }

            else if(QuerySize==8)
            {
                if("select".equals(tokens[0]))
                {
                    if("name".equals(tokens[5]))
                    {
                        DisplaySpecific(tokens[7]);
                    }
                    else if("rid".equals(tokens[5]))
                    {
                        String s=tokens[7];
                        int si=Integer.parseInt(s);
                        DisplaySpecific(si);
                    }
                    else if("where".equals(tokens[4]))
                    {
                        if(">".equals(tokens[7]))
                        {
                            String si=tokens[7];
                            Integer cs=Integer.parseInt(si);
                            DisplayGreaterThan(cs);
                        }
                        else if("<".equals(tokens[7]))
                        {
                            String si=tokens[7];
                            Integer cs=Integer.parseInt(si);
                            DisplayLessThan(cs);
                        }
                    }
                }
            }

            else if(QuerySize==10)
            {
                if("update".equals(tokens[0]))
                {
                    if("name".equals(tokens[3]))
                    {
                        UpdateSpecific(tokens[5],tokens[9]);
                    }
                    else if("salary".equals(tokens[3]))
                    {
                        String snew=tokens[5];
                        int inew=Integer.parseInt(snew);
                        UpdateSpecific(inew,tokens[9]);
                    }
                }
            }
            else
            {
                System.out.println("Please....Enter the correct query. \n Take the information with the 'help' query");
            }
        }

    }

    //Insert into table query
    public void Insertdata(String str,int value,String location)
    {
        Student sobj=new Student(str,value,location);
        lobj.add(sobj);
    }

    //Display all without condition
    public void DisplayAll()
    {
        for(Student sref:lobj)
        {
            sref.Displaydata();  //insert into student Abhi 5000 Pune
        }
    }

    //Display by rid
    public void DisplaySpecific(int rid)
    {
        for(Student sref:lobj)
        {
            if(sref.RID==rid)
            {
                sref.Displaydata();
                break;
            }
        }
        System.out.println("Query ok");
    }

    //Display by name
    public void DisplaySpecific(String str)
    {
        for(Student sref:lobj)
        {
            if(str.equals(sref.Name))
            {
                sref.Displaydata();
            }
        }
        System.out.println("Query ok");
    }

    //Delete by rid
    public void DeleteSpecific(int rid)
    {
        int index=0;
        for(Student sref:lobj)
        {
            if(sref.RID==rid)
            {
                lobj.remove(index);
                System.out.println("Query ok");
                break;
            }
            index++;
        }
    }

    //Delete by Name
    public void DeleteSpecific(String name)
    {
        int index=0;
        for(Student sref:lobj)
        {
        if(name.equals(sref.Name))
        {
            lobj.remove(index);
            System.out.println("Query ok");
            break;
        }
        index++;
    }

    }

    //Finding Maximum Salary from the table
    public void AggregateMax()
    {
        int iMax=0;
        Student temp=null;
        for(Student sref:lobj)
        {
            if(sref.Salary>iMax)
            {
                iMax=sref.Salary;
                temp=sref; //as sref is local for this function and we have to display the value outside the function so copy it in temp.
            }
            System.out.println("Infromation of student having maximum salary is");
            temp.Displaydata();
        }
    }

    //Finding minimum salary from the table
    public void AggregateMin()
    {
        int iMin=(lobj).getFirst().Salary;
        Student temp=lobj.getFirst();
        for(Student sref:lobj)
        {
            if(sref.Salary<iMin)
            {
                iMin=sref.Salary;
                temp=sref;
            }
        }
        System.out.println("Information of student having minimum salary is");
        temp.Displaydata();
    }

    //Finding sum of salary of the data
    public void AggregateSum()
    {
        long lSum =0;
        for(Student sref: lobj)
        {
            lSum = lSum+sref.Salary;
        }
        System.out.println("Summation of salary is:"+lSum);
    }

    //Finding average salary from the table
    public void AggregateAvg()
    {
        long lSum=0;
        for(Student sref:lobj)
        {
            lSum=lSum+sref.Salary;
        }
        System.out.println("Average Salary is : "+lSum/(lobj.size()));
    }

    //Finding total number of records in table
    public void AggregateCount()
    {
        System.out.println("Count is : "+lobj.size());
    }

    //update record from table by name
    public void UpdateSpecific(String str_new,String str_old)
    {
        for(Student sref:lobj)
        {
        if((sref.Name).equals(str_old))
        {
            sref.Name=str_new;
            break;
        }
    }
    System.out.println("Query ok : Updated successfully");
    }

    //Update the record by salary
    public void UpdateSpecific(int new_int,String name)
    {
        for(Student sref:lobj)
        {
            if((sref.Name).equals(name))
            {
                sref.Salary=new_int;
                break;
            }
        }
        System.out.println("Query ok : Updated Successfully");
    }

    //Finding values less than given output
    public void DisplayLessThan(int salary)
    {
        for(Student sref:lobj)
        {
            if(sref.Salary<salary)
            {
                sref.Displaydata();
            }
        }
        System.out.println("Query ok");
    }

    //Finding values greater than given input
    public void DisplayGreaterThan(int salary)
    {
        for(Student sref:lobj)
        {
            if(sref.Salary>salary)
            {
                sref.Displaydata();
            }
        }
        System.out.println("Query ok");
    }
    


}