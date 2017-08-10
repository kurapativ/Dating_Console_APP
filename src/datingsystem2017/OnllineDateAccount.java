/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datingsystem2017;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Tatha
 */
public class OnllineDateAccount {
    
    public static void MainPage()
    {
         System.out.println("***************************************Welcome to Dating Systems.Inc.***************************************");
        
        Scanner input= new Scanner(System.in);
        String selection="";
        
        while(!(selection.equals("x")))
        {
            //Display menu
            System.out.println("Please make any selection from the following");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Forgot Password ?");
            System.out.println("x. To Exit the Dating System. ");
            System.out.println("************************************************************************************************************");
            //Picking user selection
            selection=input.nextLine();
            System.out.println("************************************************************************************************************");
            
            if(selection.equals("1"))
            {
                //Sign Up functionality
                CreateNewDatingSystemAccount();
            }
            else if(selection.equals("2"))
            {
                //Login functionality
                login();
                
            }
            else if(selection.equals("3"))
            {
                //Login functionality
                 ForgotPassword();
                
            }
            else
            {
                ;
            }
            
        }
        
    }
    // SIGN UP PROCESS
    public static void CreateNewDatingSystemAccount()
    {
        Scanner input= new Scanner(System.in);
        String id="";
        String Name="";
        String Psw="";
        String Sex="";
        String City="";
        String i1="";
        String i2="";
        String i3="";
        String sq="";
        String answer="";
        int Age;
        String last_log_time="";
        int views=0;
        System.out.println("*********************welcome to Dating Suystem Sign Up page *************************************************");
        System.out.println("Please enter your name");
        Name = input.nextLine();
        System.out.println("************************************************************************************************************");
        System.out.println("Please enter your Age");
        Age = Integer.parseInt(input.nextLine());
        System.out.println("************************************************************************************************************");
        System.out.println("Please enter your Password");
        Psw = input.nextLine();
        System.out.println("************************************************************************************************************");
        
        System.out.println("Please enter your Sex");
        Sex = input.nextLine();
        System.out.println("************************************************************************************************************");
        System.out.println("Please enter your City");
        City = input.nextLine();
        System.out.println("************************************************************************************************************");
        System.out.println("Please enter your Intrest 01");
        i1=input.nextLine();
        System.out.println("************************************************************************************************************");
        System.out.println("Please enter your Intrest 02");
        i2=input.nextLine();
        System.out.println("************************************************************************************************************");
        System.out.println("Please enter your Intrest 03");
        i3=input.nextLine();
        System.out.println("************************************************************************************************************");
        System.out.println("Please enter your security question");
        sq=input.nextLine();
        System.out.println("************************************************************************************************************");
        System.out.println("Please enter your answer to security question");
        answer=input.nextLine();
        System.out.println("************************************************************************************************************");
           
         
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kurapativ5434";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
         
        try
        {
            //connect tothe databse
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "kurapativ5434", "1447544");
             //create a statement
             statement = connection.createStatement();
             //create the statement String
             
             String s = DateAndTime.DateTime()+ "\n";
             last_log_time=s;
             //to get the account number
             resultSet = statement.executeQuery("Select * "
                     + "from nextuserid");
             
             int nextid = 0;
             while(resultSet.next())
             {
                 id = "" + resultSet.getInt(1);
                 nextid = resultSet.getInt(1) + 1;
                 
             }
             int t = statement.executeUpdate("Update nextuserid set "
                     + "nextid = '" + nextid + "'");
            int r = statement.executeUpdate("insert into user values "
                    + "('" + id + "', '" + Name + "','" + Psw + "','" + Age + "','" + Sex + "','" + City + "','" + i1 + "','" + i2 + "','" + i3 + "', '" 
                     + last_log_time + "', '" + views + "','"+sq+"','"+answer+"')");
            
            
            System.out.println("***The new dating account is created!***");
            System.out.println("************************************************************************************************************");
            System.out.printf(" the ID of your Dating system account is :\t %s \n",id);
            System.out.println("************************************************************************************************************");
            System.out.println();
            System.out.println();
        }
        catch(SQLException e)
        {
             System.out.println("Account creation failed");
             e.printStackTrace();
        }
        finally
         {
             //close the database
             try
             {
                 resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
    }
    
    
    // LOGIN PROCESS
     public static void login()
    {
        //we need id and password
        Scanner input = new Scanner(System.in);
        String id="";
        String password="";
        boolean idFound = false;
        
        //get the login info.
        System.out.println("Please enter your ID");
        id = input.next();
        System.out.println("************************************************************************************************************");
        System.out.println("Please enter your password");
        password = input.next();
        System.out.println("************************************************************************************************************");
        ArrayList < UserProfile > loggeduser = new ArrayList < UserProfile > ();
        //Complete the database part below
        //database location
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kurapativ5434";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try
        {
            
            //connect to the databse
          connection = DriverManager.getConnection(DATABASE_URL, 
                  "kurapativ5434", "1447544");
            //create statement
            statement = connection.createStatement();
            //search the accountID in the onlineAccount table
            resultSet = statement.executeQuery("Select * from user where id = '" + id + "'");
            
            if(resultSet.next())
            {
                //the id is found, check the password
                if(password.equals(resultSet.getString(3)))
                {
                    //password is good
                    //go to the welcome page 
                     
                   loggeduser.add(new UserProfile(resultSet.getString("id"),resultSet.getString("name"),resultSet.getInt("age"),resultSet.getString("sex"),resultSet.getString("city"),resultSet.getString("intrest01"),resultSet.getString("intrest02"),resultSet.getString("intrest03"),resultSet.getString("last_log_time"),resultSet.getInt("views")));
                    
                    ShowUserMenu(resultSet.getString("id"),loggeduser);
                    
                    
                }
                else
                {
                    //password is not correct
                    System.out.println("The password is not correct!");
                }
            }
            else
            {
                System.out.println("The login ID is not found!");
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
        
    }
     public static void ForgotPassword()
     {
         System.out.println("*******************************Welcome to retrive your forget passwoord page ****************************************");
         System.out.println("********************************************************************************************************************");
          System.out.println("** please select from the following **");
           System.out.println("** 1. to enter the login id of the account to retrive the password **");
           System.out.println("** r. to return to Dating Systems page **");
           System.out.println("********************************************************************************************************************");
           System.out.print("enter your option:");
         Scanner input=new Scanner(System.in);
         String selection ="";
         while(!selection.equals("x"))
         {
             selection=input.nextLine();
             
             if(selection.equals("1"))
             {
                 System.out.println("please enter your login id with the account");
                 String id=input.nextLine();
                 RetrivePassword(id);
                 ForgotPassword(); 
             }
             else if(selection.equals("r"))
             {
                 MainPage();
             }
             else
             {
              ;   
             }
         }
            
         
     }
     public static void RetrivePassword(String id)
     {
         final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kurapativ5434";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;        
        Scanner input=new Scanner(System.in);             
        System.out.println("********************************************************************************************************************");

        try
        {
            //connect tothe databse
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "kurapativ5434", "1447544");
             //create a statement
             statement = connection.createStatement();
             //create the statement String
             
            resultSet = statement.executeQuery("Select sq from user where id ='" + id+ "'");
            
            while(resultSet.next())
            {
                System.out.printf("%s \n",resultSet.getString("sq"));
            }
             System.out.println("********************************************************************************************************************");
            String answer=input.nextLine();
             System.out.println("********************************************************************************************************************");
             boolean istrue=false;
             resultSet = statement.executeQuery("Select password from user where id = '" + id+ "' and answer='"+answer+"'");
             
                 
                 while(resultSet.next())
                 {
                     istrue=true;
                     System.out.printf("Your password is : %s \n",resultSet.getString("password"));
                 }
             System.out.println("********************************************************************************************************************");
            
        }
        catch(SQLException e)
        {
             System.out.println("updating views failed");
             e.printStackTrace();
        }
        finally
         {
             //close the database
             try
             {
              //   resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
     }
     public static void ShowProfile(ArrayList<UserProfile> loggeduser)
     {
         String id="";
         System.out.println("***********************************Your Dating System Profile ***************************************************");
         for(UserProfile u: loggeduser)
         {
         System.out.printf("Hello, %s",u.getName());
         System.out.printf("\t\t\tlast logged time: %s\t\t\t\t no.of views:%d \n",u.getLast_loged(),u.getViews());
         System.out.println("*******************************************************************************************************************");
         System.out.printf("your intrests: \n1.\t %s\n2.\t %s\n3.\t %s \n",u.getI1(),u.getI2(),u.getI3());
         System.out.println("********************************************************************************************************************");
         id =u.getId();
         }
         
          final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kurapativ5434";
        
        Connection connection = null;
        Statement statement = null;
       // ResultSet resultSet = null;
         
        try
        {
            //connect tothe databse
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "kurapativ5434", "1447544");
             //create a statement
             statement = connection.createStatement();
             //create the statement String
             String s = DateAndTime.DateTime()+ "\n";
             
             
             
         int t = statement.executeUpdate("Update user set "
                     + "last_log_time= '" + s + "' where id='"+id+"'");
         
            
            System.out.println();
           
        }
        catch(SQLException e)
        {
             System.out.println("updating views failed");
             e.printStackTrace();
        }
        finally
         {
             //close the database
             try
             {
              //   resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
         
     }
     public static void ShowUserMenu(String id,ArrayList<UserProfile> loggeduser )
     {
          Scanner input= new Scanner(System.in);
        String selection="";
        
        while(!(selection.equals("x")))
        {
            //Display menu
            
            for(UserProfile u:loggeduser)
            System.out.printf("\n *************************************** Welcome, %s ***************************************************\n\n",u.getName());
            System.out.println("Please make any selection from the following");
            System.out.println("1. Show my profile");
            System.out.println("2. Search Friends");
            System.out.println("3. My Friends List");
            System.out.println("4. Friend Requests");
            System.out.println("5. Popular Profiles");
            System.out.println("x. To logout the Dating System. ");
            System.out.println("***********************************************************************************************************");
            //Picking user selection
            selection=input.nextLine();
            System.out.println();
            
            if(selection.equals("1"))
            {
                //Show my profile
               
               ShowProfile( loggeduser);
               
            }
            else if(selection.equals("2"))
            {
                //Search friends
               
               
               Search(id,loggeduser);
            }
            
            else if(selection.equals("3"))
            {
                //My friends list
               MyFriends(id,loggeduser);
                
            }
             else if(selection.equals("4"))
            {
                //sent requests and received requests
                FriendRequests(id,loggeduser);
            }
             else if(selection.equals("5"))
            {
                //popular profiles
                PopularProfiles(id,loggeduser);
            }
              else if(selection.equals("x"))
            {
                //sent requests and received requests
                MainPage();
            }
            else
            {
                ;
            }
        }
     }
     public static void Search(String id,ArrayList<UserProfile> loggeduser)
     {
        
        
        Scanner input=new Scanner(System.in);
        ArrayList < String > myfriends = new ArrayList < String > ();
        ArrayList < String > searchresnames = new ArrayList < String > ();
        ArrayList < String > searchresid = new ArrayList < String > ();
        //Complete the database part below
        //database location
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kurapativ5434";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try
        {
            
            //connect to the databse
          connection = DriverManager.getConnection(DATABASE_URL, 
                  "kurapativ5434", "1447544");
            //create statement
            statement = connection.createStatement();
            //search the accountID in the onlineAccount table
            resultSet = statement.executeQuery("Select * from friendrelation where userid = '" + id + "'");
            
            
               while(resultSet.next())
               {
               
                    myfriends.add(resultSet.getString("friendid"));
                   
               }
               String query="Select * from user where";
               
               System.out.println("please enter gender preference 'male'/'female' (or) if no preference enter 'any' ");
               System.out.println("************************************************************************************************************");
               String sexpref=input.nextLine();
               System.out.println("************************************************************************************************************");
               if(!(sexpref.equals("any")))
               {
               query=query+" sex='"+sexpref+"'";
               
               }
               
               System.out.println("please enter the location preference Ex:Houston (or) if no preference enter 'any'");
               String citypref=input.nextLine();
               System.out.println("************************************************************************************************************");
               
               if(!sexpref.equals("any")&& !citypref.equals("any"))
               query=query+"\tand\t";
               
               if(!(citypref.equals("any")))
               {
                  query=query+" city='"+citypref+"'";
                  
               }     
               
               
                System.out.println("please enter the intrest preference Ex:music or food or movie (or) if no preference enter 'any'");
               String interestpref=input.nextLine();
               System.out.println("************************************************************************************************************");
                if((!sexpref.equals("any")|| !citypref.equals("any"))&&!interestpref.equals("any"))
               query=query+"\tand\t";
                
               if(!(interestpref.equals("any")))
               {
                   query=query+"(intrest01='"+interestpref+"' or intrest02='"+interestpref+"' or intrest03='"+interestpref+"')";
                  
               }
               
               System.out.println("please enter age limits first lower limit then higher limit Ex: 18,25 (or) if no preference enter 'any'");
               String agepref=input.nextLine();
               System.out.println("************************************************************************************************************");
              if((!sexpref.equals("any")|| !citypref.equals("any")||interestpref.equals("any"))&&!agepref.equals("any"))
               query=query+"\tand\t";
              
               if(!(agepref.equals("any")))
               {
               String[] agelimits=new String[2];
               agelimits=agepref.split(",");
               agepref="age between\t"+agelimits[0]+"\tand\t"+agelimits[1];
               query=query+agepref;
              
               }
               
               
               if((sexpref.equals("any")&&citypref.equals("any")&&interestpref.equals("any")&&agepref.equals("any")))
               {
                   query="Select *from user";
               }
              resultSet = statement.executeQuery(query); 
              
              
              while(resultSet.next())
              {
                  if((!id.equals(resultSet.getString("id")))&&(!myfriends.contains(resultSet.getString("id"))))
                  {
                    searchresnames.add(resultSet.getString("name"));
                    searchresid.add(resultSet.getString("id"));
                  }
                
 
             }
              int i=1;
              
               for(String sr:searchresnames)
               {
                   System.out.printf("%d. %s",i,sr);
                   System.out.println();
                   i++;
               }
               String selection="";
               System.out.println("select a user from above \n \t(or)\n r. to return to Main Page \n");
               selection=input.nextLine();
               while(!selection.equals("x"))
               {
               if(selection.equals("r"))
               {
                   ShowUserMenu(id,loggeduser);
               }
               else
               {
               
               int j= Integer.parseInt(selection);
                 ShowSearchProfile(id, searchresid.get(j-1),loggeduser);
               }
               }
                  
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
     }
     
     public static void ShowSearchProfile(String user,String search,ArrayList<UserProfile> loggeduser)
     {
         int views=0;
         String id="";
         
        ArrayList<UserProfile>searchselectprofile=new ArrayList<UserProfile>();
        
      
         final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kurapativ5434";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
         
        try
        {
            //connect tothe databse
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "kurapativ5434", "1447544");
             //create a statement
             statement = connection.createStatement();
             //create the statement String
             
            resultSet = statement.executeQuery("Select * from user where id = '" + search+ "'");
            
            while(resultSet.next())
            {
                   searchselectprofile.add(new UserProfile(resultSet.getString("id"),resultSet.getString("name"),resultSet.getInt("age"),resultSet.getString("sex"),resultSet.getString("city"),resultSet.getString("intrest01"),resultSet.getString("intrest02"),resultSet.getString("intrest03"),resultSet.getString("last_log_time"),resultSet.getInt("views")));
            }
            
          String name="";  
            for(UserProfile s:searchselectprofile)
        {
        System.out.println("********************************   Profile of selected User  *************************************************\n");
        
        System.out.printf("User Name: %s \t\t Age: %d \t\t City: %s \t\t Sex: %s \t\t No.of views: %d \n \t\t\t\t\t Last Seen: %s \n",s.getName(),s.getAge(),s.getCity(),s.getSex(),s.getViews(),s.getLast_loged());
        System.out.println("************************************************************************************************************");

        System.out.printf("Intrests are :\n 1. %s\n 2. %s\n 3. %s\n",s.getI1(),s.getI2(),s.getI3()); 
        System.out.println("************************************************************************************************************");

        views=s.getViews()+1;
        id=s.getId();
        name = s.getName();
        }
           int t = statement.executeUpdate("Update user set "
                     + "views= '" + views + "' where id='"+id+"'");
           Scanner input=new Scanner(System.in);
           System.out.println("************************************************************************************************************");
           
           ResultSet rs1=null;
           rs1=statement.executeQuery("Select msg from relationstatus where senderid='"+user+"' and status='pending' and receiverid='"+search+"'");
           String sentmsg="";
           boolean requestsent=false;
           while(rs1.next())
           {
               requestsent=true;
               sentmsg=rs1.getString("msg");
           }

           if(requestsent==false)
           {
           System.out.printf("Do you want to send friend request to %s ? \n",name);
           System.out.println("1. Yes");
           System.out.println("2. No");
           
           }
           else
           {
               System.out.println("you have already sent a request");
               System.out.printf("Your request message is : \n %s \n",sentmsg);
           }
           System.out.println("************************************************************************************************************");

           System.out.println("r. return to Main page");
           System.out.println("************************************************************************************************************");

           String selection=input.nextLine();
           System.out.println("************************************************************************************************************");
           
           while(!selection.equals("x"))
           {
               if(selection.equals("1"))
               {
                   System.out.println("Want to send some request message to user just type the message or type none");
                   String msg=input.nextLine();
                   System.out.println("************************************************************************************************************");

                   System.out.println();
                   SendFriendRequest(user,search,msg,loggeduser);
               }
               else if(selection.equals("r")||selection.equals("2"))
               {
                   ShowUserMenu(user,loggeduser);
               }
               else if(selection.equals("x"))
               {
                   MainPage();
               }
               else
               {
                   ;
               }
           }
           
           
        }
        catch(SQLException e)
        {
             System.out.println("updating views failed");
             e.printStackTrace();
        }
        finally
         {
             //close the database
             try
             {
              //   resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
     }
     public static void SendFriendRequest(String user,String search,String msg, ArrayList<UserProfile> loggeduser)
     {
         final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kurapativ5434";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
         
        try
        {
            //connect tothe databse
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "kurapativ5434", "1447544");
             //create a statement
             statement = connection.createStatement();
             //create the statement String
             
              int t = statement.executeUpdate("Insert into relationstatus values ('"+user+"','"+search+"','pending','"+msg+"')");
              System.out.println("Request sent!!!");
                     ShowUserMenu(user,loggeduser);
             }
        catch(SQLException e)
        {
             System.out.println("updating relation status failed");
             e.printStackTrace();
        }
        finally
         {
             //close the database
             try
             {
              //   resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
     }
     
     public static void FriendRequests(String user,ArrayList<UserProfile> loggeduser)
     {
         Scanner input= new Scanner(System.in);
         String selection="";
         System.out.println("************************************************************************************************************");

         System.out.println("********************All your sent and recevied friend requests can be updated here************************");
         System.out.println("1. Recevied Requests");
         System.out.println("2. Sent Requsts");
         System.out.println("r. Return to Main page");
         System.out.println("************************************************************************************************************");

         selection=input.nextLine();
        System.out.println("************************************************************************************************************");

         while(!selection.equals("x"))
         {
             if(selection.equals("1"))
             {
                 ReceivedRequests(user,loggeduser);
             }
             else if(selection.equals("2"))
             {
                 SentRequests(user,loggeduser);
             }
             else if(selection.equals("r"))
             {
                 ShowUserMenu(user,loggeduser);
             }
             else
             {
                 ;
             }
         }
                 
     }
     public static void ReceivedRequests(String user, ArrayList<UserProfile> loggeduser)
     {
         final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kurapativ5434";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
         ResultSet rs = null,rs1=null;
        try
        {
            //connect tothe databse
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "kurapativ5434", "1447544");
             //create a statement
             statement = connection.createStatement();
             //create the statement String
           System.out.println("************************************************************************************************************");

             System.out.println("***************************** Your pending received requests *********************************************");
             
              resultSet = statement.executeQuery("Select senderid from relationstatus where receiverid = '" + user+ "' and status='pending'");
              
            ArrayList<String> pendingnames=new ArrayList<String>();
            ArrayList<String> pendingids=new ArrayList<String>();
            
              while(resultSet.next())
              {
                 
                 pendingids.add(resultSet.getString("senderid"));
              }
              
              for(String a:pendingids)
              {
                  rs= statement.executeQuery("Select name from user where id = '" + a+ "'");
                  while(rs.next())
                  {
                     pendingnames.add(rs.getString("name")); 
                  }
                 
              }
              
         System.out.println("************************************************************************************************************");

             System.out.println("*************************** Select the pending request to reject (or) accept the requests *********************");
              int i=1;
              if(!pendingids.isEmpty())
              {
              for(String s:pendingnames)
              {
                  System.out.printf("%d. %s\n",i,s);
                  i++;
              }
              }

              else
              {
                  System.out.println("Sorry you dont have any pending requests");
              }
         System.out.println("************************************************************************************************************");

              System.out.println("r. To return to main menu");
         System.out.println("************************************************************************************************************");

              Scanner input= new Scanner(System.in);
              String selection=input.nextLine();
           System.out.println("************************************************************************************************************");

              
              if(selection.equals("r"))
              {
                  FriendRequests(user,loggeduser);
              }
              int j=Integer.parseInt(selection);
             
        System.out.println("************************************************************************************************************");
 
        rs1 = statement.executeQuery("Select msg from relationstatus where receiverid = '" + user+ "' and status='pending' and senderid='"+pendingids.get(j-1)+"'");
           
        String msg="";
        while(rs1.next())

        {
    
            msg=rs1.getString("msg");

        }
 System.out.printf("to accecpt the %s request select Accept (or) to reject the request select Reject \n ",pendingnames.get(j-1));
              System.out.println("1. Accept ");
              System.out.println("2. Reject");
              System.out.println("r. to return to Friend Requests page");
              System.out.printf("Request message from %s \n",pendingnames.get(j-1));
              System.out.printf("Message: %s \n",msg);
          System.out.println("************************************************************************************************************");

              selection=input.nextLine();
        System.out.println("************************************************************************************************************");

              while(!selection.equals("x"))
              {
                  if(selection.equals("1"))
                  {
                      
                       int t = statement.executeUpdate("Update relationstatus set "
                     + "status = 'accepted' where receiverid='"+user+"' and senderid='"+pendingids.get(j-1)+"'");
                       int r = statement.executeUpdate("Insert into friendrelation values ('"+user+"','"+pendingids.get(j-1)+"')");
                       int q = statement.executeUpdate("Insert into friendrelation values ('"+pendingids.get(j-1)+"','"+user+"')");
       
                       System.out.println("************************************************************************************************************");

                       System.out.printf("%s is added as your friend",pendingnames.get(j-1));
                       
                       System.out.println("************************************************************************************************************");

                       ReceivedRequests(user,loggeduser);
                  }
                  else if(selection.equals("2"))
                  {
                        int t = statement.executeUpdate("Update relationstatus set "
                     + "status = 'rejected' where receiverid='"+user+"' and senderid='"+pendingids.get(j-1)+"'");
        
                        System.out.println("************************************************************************************************************");

                        System.out.printf("%s friend request is rejected",pendingnames.get(j-1));        
                        
                        System.out.println("************************************************************************************************************");

                       ReceivedRequests(user,loggeduser);
                  }
                  else if(selection.equals("r"))
                  {
                      FriendRequests(user,loggeduser);
                  }
                  else
                  {
                      ;
                  }
              }
              
             }
        catch(SQLException e)
        {
             System.out.println("updating relation status failed");
             e.printStackTrace();
        }
        finally
         {
             //close the database
             try
             {
                resultSet.close();
               
                rs.close();
                
                rs1.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
         
     }
      public static void SentRequests(String user, ArrayList<UserProfile> loggeduser)
     {
         final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kurapativ5434";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null, rs=null, rs1=null;
         
        try
        {
            //connect tothe databse
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "kurapativ5434", "1447544");
             //create a statement
             statement = connection.createStatement();         
             System.out.println("************************************************************************************************************");

             //create the statement String
             System.out.println("************************************** Your pending sent requests *******************************************");
             
              resultSet = statement.executeQuery("Select receiverid from relationstatus where  senderid= '" + user+ "' and status='pending'");
            ArrayList<String> pendingnames=new ArrayList<String>();
            ArrayList<String> pendingids=new ArrayList<String>();
              while(resultSet.next())
              {
                  pendingids.add(resultSet.getString("receiverid"));
                 
              }
              for(String a:pendingids)
              {
                  rs= statement.executeQuery("Select name from user where id = '" + a+ "'");
                 while(rs.next())
                 {
                 pendingnames.add(rs.getString("name"));
                
                 }
              }        
              System.out.println("************************************************************************************************************");

              
             System.out.println("******************************** Select the pending request to cancel **************************************");
              int i=1;
               if(!pendingids.isEmpty())
              {
              for(String s:pendingnames)
              {
                  System.out.printf("%d. %s\n",i,s);
                  i++;
              }
              }
              else
              {
                  System.out.println("Sorry you dont have any pending requests");
              }        
               System.out.println("************************************************************************************************************");

              System.out.println("r. To return to Friend Requests menu");        
              System.out.println("************************************************************************************************************");

              
              Scanner input= new Scanner(System.in);
              String selection=input.nextLine();         
              System.out.println("************************************************************************************************************");

              
              if(selection.equals("r"))
              {
                  FriendRequests(user,loggeduser);
              }
             int j=Integer.parseInt(selection);        
             System.out.println("************************************************************************************************************");
              rs1 = statement.executeQuery("Select msg from relationstatus where  senderid= '" + user+ "' and status='pending' and receiverid='"+pendingids.get(j-1)+"'");

              String msg="";
              while(rs1.next())
              {
                  msg=rs1.getString("msg");
              }
             
              System.out.printf("to cancel your request to,%s select 'Cancel'  \n",pendingnames.get(j-1));
              System.out.println("1. Cancel ");        
              System.out.println("r. to return to Friend Requests Page "); 
              System.out.printf("Your friend request msg to user,%s \n %s \n",pendingnames.get(j-1),msg);
              System.out.println("************************************************************************************************************");

              selection=input.nextLine();         
              System.out.println("************************************************************************************************************");

              while(!selection.equals("x"))
              {
                  if(selection.equals("1"))
                  {
                       int t = statement.executeUpdate("Update relationstatus set "
                     + "status = 'cancel' where senderid='"+user+"' and recevierid='"+pendingids.get(j-1)+"'");
                       System.out.printf("%s is added as your friend",pendingnames.get(j-1));
                      
                       SentRequests(user,loggeduser);
                  }
                  else if(selection.equals("r"))
                  {
                      FriendRequests(user,loggeduser);
                  }
                  else
                  {
                      ;
                  }
              }
              
             }
        catch(SQLException e)
        {
             System.out.println("updating relation status failed");
             e.printStackTrace();
        }
        finally
         {
             //close the database
             try
             {
                 resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
         
         
     }
      public static void MyFriends(String user, ArrayList<UserProfile> loggeduser)
      {        
          System.out.println("************************************************************************************************************");

          System.out.println("********************************** Welcome to your friends list *********************************************");
          final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kurapativ5434";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null, rs=null;
         
        try
        {
            //connect tothe databse
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "kurapativ5434", "1447544");
             //create a statement
             statement = connection.createStatement();
             //create the statement String
               resultSet = statement.executeQuery("Select friendid from friendrelation where userid = '" + user+ "'");
               boolean isempty=true;
               ArrayList<String> friendids= new ArrayList<String>();
               ArrayList<String> friendnames= new ArrayList<String>();
               while(resultSet.next())
               {
                   isempty=false;
                   
                   friendids.add(resultSet.getString("friendid"));
                   
               }
               if(friendids.isEmpty())
               {        
                   System.out.println("************************************************************************************************************");

                   System.out.println("********* Sorry you dont have any friends *********\n\n");
                   String selection="";        
                   System.out.println("************************************************************************************************************");

                   Scanner input= new Scanner(System.in);
                   System.out.println("select \n r. to return to Main Menu");        
                   System.out.println("************************************************************************************************************");

                   while(!selection.equals("x"))
                   {
                       selection = input.nextLine();
                      if(selection.equals("r"))
                      {
                        ShowUserMenu(user,loggeduser);  
                      }
                      else
                      {
                          ;
                      }
                   }
                          
                   System.out.println("************************************************************************************************************");

               }
               else
               {
                   for(String f:friendids)
                   {
                       
                       rs=statement.executeQuery("Select name from user where id='"+ f+"'");
                       
                       while(rs.next())
                       {
                          friendnames.add(rs.getString("name"));  
                       }
                      
                   }
               }
               
               Scanner input=new Scanner(System.in);
               String selection="";
               
               while(!selection.equals("x"))
               {
                   
               int i=1;        
               System.out.println("************************************************************************************************************");

               System.out.println("****************************8***** Here are your friends **************************************************");
               for(String f:friendnames)
               {
                   System.out.printf("%d. %s\n",i,f);
                   i++;
               }       
               System.out.println("************************************************************************************************************");

               
               System.out.println("****** Please select a friend from your friends list  either view their profile (or) message them***********\n");
               System.out.println("select \n r. to return to Main Menu");
                       
               System.out.println("************************************************************************************************************");

               selection=input.nextLine();        
               System.out.println("************************************************************************************************************");

               if(selection.equals("r"))
               {
                   ShowUserMenu(user,loggeduser);
               }
               else
               {
                   int j=Integer.parseInt(selection);
               String friendid=friendids.get(j-1);
               String friendname=friendnames.get(j-1);
               MyFriendsProfileMessagePage(user,loggeduser,friendid,friendname);
               
               }
              
               }
               
             }
        catch(SQLException e)
        {
             System.out.println("Showing friends list failed");
        }
        finally
         {
             //close the database
             try
             {
                 resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
          
      }
      public static void MyFriendsProfileMessagePage(String user,ArrayList<UserProfile> loggeduser,String friendid,String friendname)
      {
          Scanner input=new Scanner(System.in);
          String selection="";
          
          System.out.println("******************************Welcome to Communicate to your friend Page**********************");
          
          System.out.println("Please select an option from the following");
          System.out.printf("1. To See your friend %s profile \n",friendname);
          System.out.printf("2. To Message your friend %s \n",friendname);
          System.out.println("r. To return to Friends List Page");
                    
          System.out.println("*******************************************************************************************************");

          while(!selection.equals("r"))
          {
              selection=input.nextLine();
              if(selection.equals("1"))
              {
                  ShowFriendProfile(user,loggeduser,friendid,friendname);
              }
              else if(selection.equals("2"))
              {
                  MessageFriend(user,loggeduser,friendid,friendname);
              }
              else if(selection.equals("r"))
              {
                  MyFriends(user,loggeduser);
              }
              else
              {
                  ;
              }
          }
      }
      public static void ShowFriendProfile(String user, ArrayList<UserProfile> loggeduser, String friendid, String friendname)
      {
        int views=0;
         String id="";
         
        ArrayList<UserProfile>friendprofile=new ArrayList<UserProfile>();
        
      
         final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kurapativ5434";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
         
        try
        {
            //connect tothe databse
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "kurapativ5434", "1447544");
             //create a statement
             statement = connection.createStatement();
             //create the statement String
             
            resultSet = statement.executeQuery("Select * from user where id = '" + friendid+ "'");
            
            while(resultSet.next())
            {
                   friendprofile.add(new UserProfile(resultSet.getString("id"),resultSet.getString("name"),resultSet.getInt("age"),resultSet.getString("sex"),resultSet.getString("city"),resultSet.getString("intrest01"),resultSet.getString("intrest02"),resultSet.getString("intrest03"),resultSet.getString("last_log_time"),resultSet.getInt("views")));
            }
            
          String name="";  
            for(UserProfile s:friendprofile)
        {
        System.out.println("********************************   Profile of selected User  *************************************************\n");
        
        System.out.printf("User Name: %s \t\t Age: %d \t\t City: %s \t\t Sex: %s \t\t No.of views: %d \n \t\t\t\t Last Seen: %s \n",s.getName(),s.getAge(),s.getCity(),s.getSex(),s.getViews(),s.getLast_loged());
        System.out.println("************************************************************************************************************");

        System.out.printf("Intrests are :\n 1. %s\n 2. %s\n 3. %s\n",s.getI1(),s.getI2(),s.getI3()); 
        System.out.println("************************************************************************************************************");

        views=s.getViews()+1;
        id=s.getId();
        name = s.getName();
        }
           int t = statement.executeUpdate("Update user set "
                     + "views= '" + views + "' where id='"+id+"'");
           Scanner input=new Scanner(System.in);
           String selection="";
           System.out.println("************************************************************************************************************");
            System.out.println("r. return to Friends Communication page");
           System.out.println("************************************************************************************************************");

           while(!selection.equals("x"))
           {
               selection=input.nextLine();
           if(selection.equals("r"))
           {
               MyFriendsProfileMessagePage( user,loggeduser,friendid,friendname);
           }
           else
           {
               ;
           }
           }
        }
        
        catch(SQLException e)
        {
             System.out.println("updating views failed");
             e.printStackTrace();
        }
        finally
         {
             //close the database
             try
             {
              //   resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
        
      }
      public static void MessageFriend(String user, ArrayList<UserProfile> loggeduser, String friendid, String friendname)
      {        
          System.out.println("************************************************************************************************************");

             System.out.printf("******************************* Welcome to your message history page with %s *************************\n\n",friendname);
          final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kurapativ5434";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
         
        try
        {
            //connect tothe databse
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "kurapativ5434", "1447544");
             //create a statement
             statement = connection.createStatement();
             //create the statement String
               resultSet = statement.executeQuery("Select * from msg where (sendid = '" + user+ "'" +"and receiveid='"+friendid+"') or (sendid = '" + friendid+ "'" +"and receiveid='"+user+"')");
              String username="";
              boolean isempty=true;
               while(resultSet.next())
               {
                   isempty=false;
                   
                   if(user.equals(resultSet.getString("sendid")) || friendid.equals(resultSet.getString("receiveid")) )
                   {
                       
                       for(UserProfile u:loggeduser)
                       {
                           username = u.getName();
                       }
                        System.out.printf("%s: %s \n",username,resultSet.getString("content"));
                       System.out.printf(" \t\t\t :%s \n",resultSet.getString("dateandtime"));
                   }
                   else if(user.equals(resultSet.getString("receiveid"))|| friendid.equals(resultSet.getString("sendid")) )
                   {
                       String fname= friendname;
                       System.out.printf("%s: %s \n",fname,resultSet.getString("content"));
                       System.out.printf(" \t\t\t :%s\n ",resultSet.getString("dateandtime"));
                       
                   }
                   else
                   {
                       ;
                   }
                           
               }        
               System.out.println("************************************************************************************************************");

               
               if(isempty==true)
               {
                   System.out.printf("******* Sorry you don't have any message history with, %s **********\n\n",friendname);
               }
                       
               System.out.println("************************************************************************************************************");

               System.out.printf("To send message to %s , please select \n 1.Message\n r. return to Friends Communication page\n  ", friendname);        
               
               System.out.println("************************************************************************************************************");

               
               
               
               Scanner input=new Scanner(System.in);
               String selection="";
               while(!selection.equals("x"))
               {
                   selection=input.nextLine();
                   if(selection.equals("1"))
                   {
                       for(UserProfile u:loggeduser)
                       {
                           username = u.getName();
                       }
                       System.out.printf("please start typing...... \n%s:",username);
                       String msg=input.nextLine();
                       String s = DateAndTime.DateTime()+ "\n";
                       
                      
               
                       int r=statement.executeUpdate("Insert into msg values('"+user+"','"+friendid+"','"+msg+"','"+s+"','"+username+"','"+friendname+"')");
                       MessageFriend(user,loggeduser,friendid,friendname);
                   }
                   else if(selection.equals("r"))
                   {
                        MyFriendsProfileMessagePage( user,loggeduser,friendid,friendname);
                       
                   }
                   else
                   {
                       ;
                   }
               }
                       
               System.out.println("************************************************************************************************************");

             }
        catch(SQLException e)
        {
             System.out.println("updating messages failed");
             e.printStackTrace();
        }
        finally
         {
             //close the database
             try
             {
                 resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
           
       
          
      }
      public static void PopularProfiles(String user, ArrayList<UserProfile> loggeduser)
      {
          System.out.println("************************** Welcome to Popular Profiles Page ***************************");
          Scanner input=new Scanner(System.in);
          String selection="";
          
          System.out.println("select one of the following options");
           System.out.println("1. Top three Female Profiles");
           System.out.println("2. Top three Male Profiles");
          System.out.println("r. To return to Main Page");
          System.out.println("***************************************************************************************");
          
          while(!selection.equals("x"))
          {
              selection=input.nextLine();
              if(selection.equals("1"))
              {
                  TopFemaleProfiles(user,loggeduser);
              }
              else if(selection.equals("2"))
              {
                  TopMaleProfiles(user,loggeduser);
              }
              else if(selection.equals("r"))
              {
                  ShowUserMenu(user,loggeduser);
              }
              else
              {
                  ;
              }
          }
          
      }
      public static void TopFemaleProfiles(String user,ArrayList<UserProfile> loggeduser)
      {
          System.out.println("******************************* Welcome Top Female Profiles ************************************");
          
           final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kurapativ5434";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
         
        try
        {
            //connect tothe databse
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "kurapativ5434", "1447544");
             //create a statement
             statement = connection.createStatement();
             //create the statement String
             resultSet = statement.executeQuery("Select * from user where sex='female' order by views desc");
            
             ArrayList<UserProfile> topfemale = new  ArrayList<UserProfile>();
             
             while(resultSet.next())
             {
                                    
                 topfemale.add(new UserProfile(resultSet.getString("id"),resultSet.getString("name"),resultSet.getInt("age"),resultSet.getString("sex"),resultSet.getString("city"),resultSet.getString("intrest01"),resultSet.getString("intrest02"),resultSet.getString("intrest03"),resultSet.getString("last_log_time"),resultSet.getInt("views")));
 
             }
             
             int i=1;
             for(UserProfile u: topfemale)
             {
                 if(i<4)
                 {
                    System.out.printf("%d. %s \t\t No.of views: %d\n",i,u.getName(),u.getViews()); 
                 }
                 i++;
             }
             
             
             System.out.println("Select a user from above  to view the profile");
             System.out.println("r. to return to Top Profile page");
             System.out.println("x. to return to Main page");
             
             Scanner input= new Scanner(System.in);
             String selection="";
             System.out.println("**************************************************************************************************");  
              selection=input.nextLine();
              
              if(selection.equals("r"))
              {
                  PopularProfiles(user,loggeduser);
              }
              else if(selection.equals("x"))
              {
                  ShowUserMenu(user,loggeduser);
              }
              else
              {
                  int j=Integer.parseInt(selection);
                  DisplayTopProfile(user, loggeduser,topfemale.get(j-1));
              }
             
         System.out.println("**************************************************************************************************");     
        }
           catch(SQLException e)
        {
             System.out.println("Showing friends list failed");
        }
        finally
         {
             //close the database
             try
             {
                 resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
          
         System.out.println("**************************************************************************************************"); 
      }
              
      public static void TopMaleProfiles(String user,ArrayList<UserProfile> loggeduser)
      {
          System.out.println("*************************** Welcome Top Male Profiles *********************************************"); 
          
             
           final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kurapativ5434";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
         
        try
        {
            //connect tothe databse
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "kurapativ5434", "1447544");
             //create a statement
             statement = connection.createStatement();
             //create the statement String
              
                 resultSet = statement.executeQuery("Select * from user where sex='male' order by views desc");
            
             ArrayList<UserProfile> topmale = new  ArrayList<UserProfile>();
             
             while(resultSet.next())
             {
                                    
                 topmale.add(new UserProfile(resultSet.getString("id"),resultSet.getString("name"),resultSet.getInt("age"),resultSet.getString("sex"),resultSet.getString("city"),resultSet.getString("intrest01"),resultSet.getString("intrest02"),resultSet.getString("intrest03"),resultSet.getString("last_log_time"),resultSet.getInt("views")));
 
             }
             
             int i=1;
             for(UserProfile u: topmale)
             {
                 if(i<4)
                 {
                    System.out.printf("%d. %s \t\t No.of views: %d\n",i,u.getName(),u.getViews()); 
                 }
                 i++;
             }
             
             
             System.out.println("Select a user from above  to view the profile");
             System.out.println("r. to return to Top Profile page");
             System.out.println("x. to return to Main page");
             
             Scanner input= new Scanner(System.in);
             String selection="";
             System.out.println("**************************************************************************************************");  
              selection=input.nextLine();
              
              if(selection.equals("r"))
              {
                  PopularProfiles(user,loggeduser);
              }
              else if(selection.equals("x"))
              {
                  ShowUserMenu(user,loggeduser);
              }
              else
              {
                  int j=Integer.parseInt(selection);
                  DisplayTopProfile(user, loggeduser,topmale.get(j-1));
              }
            
        }
           catch(SQLException e)
        {
             System.out.println("Showing friends list failed");
        }
        finally
         {
             //close the database
             try
             {
                 resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
          
          
          System.out.println("*****************************************************************************************************");
      }
      
      public static void DisplayTopProfile(String user, ArrayList<UserProfile> loggeduser, UserProfile topprofile )
      {
          int views=0;
         String id="";
         
       
        
      
         final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kurapativ5434";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
         
        try
        {
            //connect tothe databse
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "kurapativ5434", "1447544");
             //create a statement
             statement = connection.createStatement();
             //create the statement String 
           
        System.out.printf("********************************   Profile of %s *************************************************\n",topprofile.getName());
        
        System.out.printf("User Name: %s \t\t Age: %d \t\t City: %s \t\t Sex: %s \t\t \n No.of views: %d  \t\t\t\t\t\t Last Seen: %s \n",topprofile.getName(),topprofile.getAge(),topprofile.getCity(),topprofile.getSex(),topprofile.getViews(),topprofile.getLast_loged());
        System.out.println("************************************************************************************************************");

        System.out.printf("Intrests are :\n 1. %s\n 2. %s\n 3. %s\n",topprofile.getI1(),topprofile.getI2(),topprofile.getI3()); 
        System.out.println("************************************************************************************************************");

        views=topprofile.getViews()+1;
        id=topprofile.getId();
        
        
           int t = statement.executeUpdate("Update user set "
                     + "views= '" + views + "' where id='"+id+"'");
           Scanner input=new Scanner(System.in);
           String selection="";
           System.out.println("************************************************************************************************************");
            System.out.println("r. return to Top profiles page");
           System.out.println("************************************************************************************************************");

           while(!selection.equals("x"))
           {
               selection=input.nextLine();
           if(selection.equals("r"))
           {
               PopularProfiles(user, loggeduser);
           }
           else
           {
               ;
           }
           }
           
            
        }
           catch(SQLException e)
        {
             System.out.println("Showing friends list failed");
        }
        finally
         {
             //close the database
             try
             {
                 resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
      }
}