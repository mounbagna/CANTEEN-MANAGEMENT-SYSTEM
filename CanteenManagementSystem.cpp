#include<iostream>
#include<iomanip>
#include<string>
#include<cstring>
#include<cstdlib>
#include<cstdio>
#include<iostream>
#include<fstream>
#include<sstream>
#include<typeinfo>
#include <cmath>
using namespace std;
struct order
{
      int prodid1;
      char pname1[50];
      char compy1[50];
      int qty1;
      float price1,dis1;
}o1[50];
int orderk=0;
void copyme(int k,order order1[],int q1,int &c2);
void againopenandclose();

class person
{
    char name[100];
public:
    person(){}
    virtual int Login()=0;
    void setName(char *n)
    {
        strcpy(name,n);
    }
    char *getname()
    {
        return name;
    }
};
class product
{

      int prodid;
      char name[50];
      char company[50];
      int qty;
      float price;
    public:

        int search(int p);
      product()
      {

            qty=0;
            price=0;
      }
      void modify_record(int n);
      void delete_record(int n);
      void product_menu();
      int getproduct();
      void write_book();
      void display_sp(int n);
      void prod_tabular();
      void product_detail_heading();
      void changeqty(int pr1,int q11);
      void modifydata(int n1,char snm[15],float pri,char companynm[15],int q);
      void create_prod(int rn1)
      {
             cout<<"-------------------------------------------------------------------------"<<endl;
             cout<<"PRODUCT NO: ";
             prodid=rn1;
             cout<<prodid<<endl;
             cout<<"NAME OF PRODUCT:"<<endl;
             cin>>name;
             cout<<"COMPANY:"<<endl;
             cin>>company;
             cout<<"PRODUCT PRICE:"<<endl;
             cin>>price;
             cout<<"QUANTITY:"<<endl;
             cin>>qty;
             cout<<"-------------------------------------------------------------------------"<<endl;
      }
      void show_prod()
      {
             cout<<"-------------------------------------------------------------------------"<<endl;
             cout<<"PRODUCT NO            : "<<prodid<<endl;
             cout<<"NAME OF PRODUCT       : "<<name<<endl;
             cout<<"COMPANY               : "<<company<<endl;
             cout<<"PRODUCT PRICE         : "<<price<<endl;
             cout<<"QUANTITY        : "<<qty<<endl;
             cout<<"-------------------------------------------------------------------------"<<endl;
       }
      //Function shows product data in tabular form
      void showall(int c)
      {
            cout<<"  "<<prodid<<setw(15)<<name<<setw(11)<<company<<setw(11)<<"BDT."<<price<<setw(10)<<qty<<endl;
      }
      int retpno()
      {
           return prodid;
      }
      float retprice()
      {
           return price;
      }
      char* getcompany()
      {
          return company;
      }
      char* getpname()
      {
          return name;
      }
      int getqty()
      {
          return qty;
      }
      void setqty(int q21)
      {
          qty=q21;
      }
};
class customer:virtual public person
{
      int cust_id;
      char cname[100];
      int id;
public:
    int before_order();
    void delete_record(int n);
int Login();
void write_customer1();
int searchcust(int p);
void write_customer();
int getcustomers();
void customermenu();
void againopenandclosecust();
void customer_menu();
void customer_detail_heading();
void modify_record(int n);
void display_cust_sp(int n);
void cust_tabular();
void modify_cust_record(int n);
void deletecust_record(int n);
      void modifycust_data(int n1,char nm[15],int sid);
      int getcustid()
      {
            return cust_id;
      }
      char *getcustnm()
      {
            return cname;
      }
      int getcustadd()
      {
            return id;
      }

      //function for taking input from customer
      void cust_input(int custid)
      {
            cout<<"-------------------------------------------------------------------------"<<endl;
            cout<<"CUSTOMER NO: ";
            cust_id=custid;
            cout<<cust_id<<endl;
            cout<<"NAME OF CUSTOMER:"<<endl;
            cin>>cname;
            cout<<"ID:"<<endl;
            cin>>id;
            cout<<"-------------------------------------------------------------------------"<<endl;
      }
      //function to show customer details
      void showallcust(int c)
      {
            cout<<"   "<<cust_id<<setw(15)<<cname<<setw(23)<<id<<endl;
      }
      void show_cust()
      {
             cout<<"-------------------------------------------------------------------------"<<endl;
             cout<<"CUSTOMER NO      : "<<cust_id<<endl;
             cout<<"NAME OF CUSTOMER : "<<cname<<endl;
             cout<<"ID    : "<<id<<endl;

             cout<<"-------------------------------------------------------------------------"<<endl;
      }
};
//customer class ends here
class administrator:public product,public customer,public person
{
private:
public:
    int Login();
    void administratormenu();
};
//Function to modify the customer details
void product::modifydata(int n1,char snm[15],float pri,char companynm[15],int q)
{
      char tmpnm[40],tmpnm2[40];
      char yes1,yes2;
       cout<<"PRODUCT NO: ";
       prodid=n1;
       cout<<prodid<<endl;
       strcpy(name,snm);
       cout<<"NAME OF PRODUCT:"<<endl;
       cout<<name<<endl;
       cout<<"Want to change the name of product ? (Yes[ y or Y ] or NO [n or N])"<<endl;
       int flag=0;
       while(1)
       {
            cin>>yes1;
            if(yes1== 'Y' || yes1== 'y')
            {
                  cout<<"Enter new name\n";
                  cin>>tmpnm;
                  flag=1;
                  break;
            }
            if(yes1== 'N' || yes1== 'n')
            {
                  flag=0;
                  break;
            }
       }
       if(flag==1)
       {
            strcpy(name,tmpnm);
       }
       strcpy(company,companynm);
       //COMPANY NAME TO BE MODIFY
       cout<<"COMPANY NAME:"<<endl;
       cout<<company<<endl;
       cout<<"Want to change the company name ? (Yes[ y or Y ] or NO [n or N])"<<endl;
      flag=0;
      while(1)
      {
            cin>>yes2;
            if(yes2== 'Y' || yes2== 'y')
            {
                  cout<<"Enter new company name:\n";
                  cin>>tmpnm2;
                  flag=1;
                  break;
            }
            if(yes2== 'N' || yes2== 'n')
            {
                  flag=0;
                  break;
            }
      }
      if(flag==1)
      {
            strcpy(company,tmpnm2);
      }
      price=pri;
      cout<<"PRICE:"<<endl;
      cout<<price<<endl;
      float tmppr=0;
      char yes4,yes3,yes5;
      cout<<"Want to change the price of product ? (Yes[ y or Y ] or NO [n or N])"<<endl;
      flag=0;
      while(1)
      {
            cin>>yes3;
            if(yes3== 'Y' || yes3== 'y')
            {
                  cout<<"Enter new price of product:\n";
                  cin>>tmppr;
                  flag=1;
                  break;
            }
            if(yes3== 'N' || yes3== 'n')
            {
                  flag=0;
                  break;
            }
      }
      if(flag==1)
      {
            price=tmppr;
      }
      qty=q;
      cout<<"QUANTITY:"<<endl;
      cout<<qty<<endl;
      int tmpqty=0;
      cout<<"Want to change the quantity of product ? (Yes[ y or Y ] or NO [n or N])"<<endl;
      flag=0;
      while(1)
      {
            cin>>yes4;
            if(yes4== 'Y' || yes4== 'y')
            {
                  cout<<"Enter new quantity:\n";
                  cin>>tmpqty;
                  flag=1;
                  break;
            }
            if(yes4== 'N' || yes4== 'n')
            {
                  flag=0;
                  break;
            }
      }
      if(flag==1)
      {
            qty=tmpqty;
      }
      if((yes3== 'Y' || yes3== 'y') || (yes2== 'Y' || yes2== 'y') || (yes1== 'Y' || yes1== 'y') || (yes4== 'Y' || yes4== 'y'))
            cout<<"*********************   NEW PRODUCT RECORD SAVED   *********************"<<endl;
      else
            cout<<"********************   NO PRODUCT RECORD CHANGED   *********************"<<endl;
}
void customer::modifycust_data(int n1,char nm[15],int sid)
{
      char tmpnm[40];
      int tmpnm2;
      char yes1,yes2;
      cust_id=n1;
      strcpy(cname,nm);
      cout<<"NAME OF CUSTOMER:"<<endl;
      cout<<cname<<endl;
      cout<<"Want to change the name of customer ? (Yes[ y or Y ] or NO [n or N])"<<endl;
      int flag=0;
      while(1)
      {
            cin>>yes1;
            if(yes1== 'Y' || yes1== 'y')
            {
                  cout<<"Enter new name\n";
                  cin>>tmpnm;
                  flag=1;
                  break;
            }
            if(yes1== 'N' || yes1== 'n')
            {
                  flag=0;
                  break;
            }
      }
      if(flag==1)
      {
            strcpy(cname,tmpnm);
      }
      id=sid;
      cout<<"SCHOOL ID:"<<endl;
      cout<<id<<endl;
      cout<<"Want to change the school id ? (Yes[ y or Y ] or NO [n or N])"<<endl;
      flag=0;
      while(1)
      {
            cin>>yes2;
            if(yes2== 'Y' || yes2== 'y')
            {
                  cout<<"Enter new school id\n";
                  cin>>tmpnm2;
                  flag=1;
                  break;
            }
            if(yes2== 'N' || yes2== 'n')
            {
                  flag=0;
                  break;
            }
      }
      if(flag==1)
      {
            id=tmpnm2;
      }


      if((yes2== 'Y' || yes2== 'y') || (yes1== 'Y' || yes1== 'y'))
            cout<<"*********************   NEW CUSTOMER RECORD SAVED   **********************"<<endl;
      else
            cout<<"********************   NO CUSTOMER RECORD CHANGED   **********************"<<endl;
}
//Function to add the records in file
void customer::write_customer()
{
      ofstream objoff;
      customer cobj;
      objoff.open("customer.csv",ios::out|ios::app);
      int r=getcustomers();
      if(r>100) //1000
      {
            r=1; // r=100
      }
       cobj.cust_input(r);
       objoff.write((char*)&cobj,sizeof(customer));
       objoff.close();
       cout<<"***********************   CUSTOMER RECORD SAVED   ***********************"<<endl;
       cin.ignore();
       cin.get();
}
//Function to check the customer number already given or not
int customer::getcustomers()
{
      ifstream objiff;
      customer cust;
      int count=0;
      objiff.open("customer.csv",ios::binary);
      objiff.seekg(0,ios::beg);
      if(!objiff)
      {
            cout<<"File could not be open !! Press any Key..."<<endl;
            cin.get();
      }
      while(objiff.read((char *) &cust, sizeof(customer)))
      {
            count++;
      }
      //***********jump to the last line
      objiff.seekg(count-sizeof(cust),ios::beg);
      objiff.read((char *) &cust, sizeof(customer));
      count=cust.getcustid();
      count++;
      objiff.close();
      return count;
}
// Function to read specific record from file
void customer::display_cust_sp(int n)
{
       ifstream objfp;
       customer cust;
       int flag=0;
       objfp.open("customer.csv",ios::binary);
       if(!objfp)
      {
            cout<<"File could not be open !! Press any Key..."<<endl;
            cin.get();
            return;
      }
      while(objfp.read((char*)&cust,sizeof(customer)))
      {
             if(cust.getcustid()==n)
            {
                  cust.show_cust();
                  flag=1;
            }
      }
      objfp.close();
      if(flag==0)
      cout<<"\n\nRecord doesnot exist"<<endl;
      cin.get();
}
//FUNCTION TO DISPLAY ALL THE CUSTOMER TABULAR FORM
void customer::cust_tabular()
{
      int r=0,col=10;
      customer cust;
      ifstream inFile;
      inFile.open("customer.csv",ios::binary);
      if(!inFile)
      {
            cout<<"File could not be open !! Press any Key..."<<endl;
            cin.get();
            return;
      }
      customer_detail_heading();
      while(inFile.read((char *) &cust, sizeof(customer)))
      {
             if(r<=12)
             {
                   r++;
                   cust.showallcust(col);
                   col++;
             }
             else
             {
                   cout<<"----------------------------- Press any key -----------------------------"<<endl;
                   cin.get();
                   customer_detail_heading();
                   col=10;
                   r=0;
            }
      }
      inFile.close();
      cin.get();
}
//function to display heading of customer details
void customer::customer_detail_heading()
{
      cout<<"========================================================================="<<endl;
      cout<<"   ************************  CUSTOMER DETAILS  **********************    "<<endl;
      cout<<"========================================================================="<<endl;
      cout<<"CUST.NO"<<setw(13)<<"NAME"<<setw(23)<<"ADDRESS"<<endl;
      cout<<"-------------------------------------------------------------------------"<<endl;
}
//FUNCTION TO MODIFY customer RECORD
void customer::modify_cust_record(int n)
{
      customer cust,temp;
      char tmpnm[50];
      int tmpid;
      ifstream inFile;
      int fpos1=-1;
      inFile.open("customer.csv",ios::binary);
      if(!inFile)
      {
            cout<<"File could not be open !! Press any Key..."<<endl;
            cin.get();
            return;
      }
      int flag=0;
      while(inFile.read((char *) &cust, sizeof(customer)))
      {
            if(cust.getcustid()==n)
            {
             cust.show_cust();
             flag=1;
            }
      }
      inFile.close();
      if(flag==0)
            cout<<"\n\nRecord doesnot exist"<<endl;
      else
      {
      //modifying the records starts here
            fstream File;
            File.open("customer.csv",ios::binary|ios::in|ios::out);
            if(!File)
            {
                  cout<<"File could not be open !! Press any Key..."<<endl;
                  cin.get();
                  return;
            }
            while(File.read((char *) &cust, sizeof(customer)))
            {
                  if(cust.getcustid()==n)
                  {
                        fpos1=(int)File.tellg();
                        break;
                  }
            }
            File.seekp(fpos1-sizeof(customer),ios::beg);
            strcpy(tmpnm,cust.getcustnm());
            tmpid=cust.getcustadd();


            cout<<"============  ENTER NEW VALUES FOR THE RECORDS GIVEN ABOVE  ============="<<endl;
            temp.modifycust_data(n,tmpnm,tmpid);
            File.write((char *) &temp, sizeof(customer));
            File.close();
      }
}
//FUNCTION TO DELETE THE RECORD OF THE CUSTOMER AVAILABLE
void customer::deletecust_record(int n)
{
      customer cust;
      ifstream inFile;
      inFile.open("customer.csv",ios::binary);
      if(!inFile)
      {
            cout<<"File could not be open !! Press any Key..."<<endl;
            cin.get();
            return;
      }
      int flag=0;
      while(inFile.read((char *) &cust, sizeof(customer)))
      {
            if(cust.getcustid()==n)
            {
                   cust.show_cust();
                   flag=1;
            }
      }
      inFile.close();
      char ch;
      if(flag==0)
            cout<<"\n\nRecord doesnot exist"<<endl;
      else
      {
      //Deletion of the records starts from here
            cout<<"DO YOU WANT TO DELETE THE RECORDS GIVEN ABOVE [YES(Y or y) OR NO(N or n)]"<<endl;
            cin>>ch;
            if (toupper(ch)=='Y')
            {
                   ofstream outFile;
                   outFile.open("Temp2.csv",ios::binary);
                   ifstream objiff("customer.csv",ios::binary);
                   objiff.seekg(0,ios::beg);
                   while(objiff.read((char *) &cust, sizeof(customer)))
                   {
                         if(cust.getcustid()!=n)
                         {
                              outFile.write((char *) &cust, sizeof(customer));
                         }
                  }
                  outFile.close();
                  objiff.close();
                  remove("customer.csv");
                  rename("Temp2.csv","customer.csv");
                  //againopenandclosecust();
                  cout<<"---------------------------Record Deleted--------------------------------"<<endl;
            }
      }
      cin.get();
}
void againopenandclosecust()
{
      ifstream inFile;
      customer cust;
      inFile.open("customer.csv",ios::binary);
      if(!inFile)
      {
            cin.get();
            return;
      }
      while(inFile.read((char *) &cust, sizeof(customer)))
      {
      }
      inFile.close();
}
//Search the customer
int customer::searchcust(int p)
{
      customer cust;
      int tmprt=0;
      ifstream inFile;
      inFile.open("customer.csv",ios::binary);
      if(!inFile)
      {
            cout<<"File could not be open !! Press any Key..."<<endl;
            cin.get();
            return -1;
      }
      int flag=0;
      while(inFile.read((char *) &cust, sizeof(customer)))
      {
            if(cust.getcustid()==p)
            {
                   cust.show_cust();
                   flag=1;
                   tmprt=(int)inFile.tellg();
                   break;
            }
      }
      inFile.close();
      if(flag==0)
            return 1;
      //cout<<"\n\nRecord doesnot exist";
      else
      {
            return tmprt;
      }
}
//Fuction to write customer data
void customer::write_customer1()
{
      ofstream objoff;
      customer cobj;
      objoff.open("customer.csv",ios::out|ios::app);
      int r=getcustomers();
      if(r>100) //1000
      {
            r=1; // r=100
      }
       cobj.cust_input(r);
       objoff.write((char*)&cobj,sizeof(customer));
       objoff.close();
       cout<<"***********************   CUSTOMER RECORD SAVED   ***********************"<<endl;
       cin.ignore();
       cin.get();
       customer_menu();
}
int customer::before_order()
{
      int f=-1,num=0;
      customer cust;
      cout<<"ENTER THE CUSTOMER ID TO BILL:"<<endl;
      cin>>num;
      ifstream inFile;
      inFile.open("customer.csv",ios::binary);
      if(!inFile)
      {
            cout<<"File could not be open !! Press any Key..."<<endl;
            cin.get();
            return -1;
      }
      while(inFile.read((char *) &cust, sizeof(customer)))
      {
            if(cust.getcustid()==num)
            {
                   cust.show_cust();
                   f=1;
                   //tmprt=(int)inFile.tellg();
                   break;
            }
      }
      inFile.close();
      return f;
}

// Global declaration for stream object
fstream fp;
// Class function outside
product pr;
// Function to write product details in file
void product::write_book()//create product details
{
      fp.open("product.csv",ios::out|ios::app);
      int rnn=getproduct();
      if(rnn>100)
      {
            rnn=1;
      }
      pr.create_prod(rnn);
      fp.write((char*)&pr,sizeof(product));
      fp.close();
      cout<<"***********************  PRODUCTS RECORD SAVED  ************************"<<endl;
      cin.ignore();
      cin.get();
}
//Function to check the product number already given or not
int product::getproduct()
{
      ifstream objiff;
      product st;
      int count=0;
      objiff.open("product.csv",ios::binary);
      objiff.seekg(0,ios::beg);
      if(!objiff)
      {
            cout<<"File could not be open !! Press any Key..."<<endl;
            cin.get();
      }
      while(objiff.read((char *) &st, sizeof(product)))
      {
            count++;
      }
      objiff.seekg(count-sizeof(st),ios::beg);
      objiff.read((char *) &st, sizeof(product));
      count=st.retpno();
      count++;
      objiff.close();
      return count;
}
//Function to read specific record from file
void product::display_sp(int n)
{
      int flag=0;
      product pr;
      fp.open("product.csv",ios::in);
      if(!fp)
      {
            cout<<"File could not be open !! Press any Key..."<<endl;
            cin.get();
            return;
      }
      while(fp.read((char*)&pr,sizeof(product)))
      {
            if(pr.retpno()==n)
            {
                  pr.show_prod();
                  flag=1;
            }
      }
      fp.close();
      if(flag==0)
            cout<<"\n\nRecord doesnot exist"<<endl;
      cin.get();
}
//Function to place order and generating invoice for PRODUCT PURCHASED
void place_order()
{
      order o1[50];
      product ob;
      customer ob1;
      int c=0,pr1=0;
      float amt=0,total=0,ttaxt=0;
      int k=0,q1;
      char ch='Y';
      int ptx[100];
      int v=0;
      int value=ob1.before_order();
      if(value==1)
      {
            cout<<endl;


            do
            {
                  ob.prod_tabular();
                   cout<<"========================================================================"<<endl;
            cout<<"                             PLACE YOUR ORDER                           "<<endl;
            cout<<"========================================================================"<<endl;
                  cout<<"ENTER THE PRODUCT NO: "<<endl;
                  cin>>pr1;
                  k=ob.search(pr1);

                  if(k>0)
                  {
                        cout<<"Enter the Quantity:"<<endl;
                        cin>>q1;
                        copyme(k,o1,q1,c);
                        ptx[v]=pr1;
                        v++;
                  }
                  else
                  {
                        cout<<"PRODUCT not found"<<endl;
                  }
                  cout<<"Do you want purchase more ? (Yes[ y or Y ] or NO [n or N])"<<endl;
                  cin>>ch;
            }while(ch=='y' || ch=='Y');
            cout<<"Thank You For Placing The Order  ........"<<endl<<endl;
            cin.get();
            cout<<"========================================================================\n"<<endl;
            cout<<"*****************************   INVOICE   ******************************"<<endl;
            cout<<"------------------------------------------------------------------------"<<endl;
            cout<<"PR.No."<<setw(7)<<"NAME"<<setw(10)<<"Qty"<<setw(12)<<"Price"<<setw(13)<<"Amount"<<endl<<endl;
            int yy=8;
            for(int x=0;x<c;x++)
            {
                  amt=o1[x].qty1*o1[x].price1;

                  cout<<"  "<<ptx[x]<<setw(10)<<o1[x].pname1<<setw(9)<<o1[x].qty1<<setw(12)<<"BDT."<<o1[x].price1<<setw(10)<<"BDT."<<amt<<setw(14)<<endl;
                  total+=amt;

                  yy++;
             }
             ttaxt=18;
             cout<<"\n-------------------------------------------------------------------------"<<endl;
             yy++;
             cout<<"\n		  TOTAL AMOUNT     :   "<<"BDT."<<total<<endl;
             yy++;
             cout<<"-------------------------------------------------------------------------"<<endl;
             cout<<"		   P A Y M E N T  S U M M A R Y  "<<endl;
             cout<<"-------------------------------------------------------------------------"<<endl;
             cout<<"		  Enter CASH value :   BDT.";
             float vb,xy;
             cin>>vb;
             xy=(vb-total);
             if(xy<0)
             {
                cout<<"\nSorry! You have paid Insufficient cash. Please reinitiate billing. Thank You."<<endl;
                ob1.customermenu();
             }
             else
             {
                cout<<"	     Change to be returned :   BDT."<<xy<<endl;
                ob.changeqty(pr1,q1);
                cout<<"-------------------------------------------------------------------------"<<endl;
             cout<<"\n\n	   WE ARE EAGERLY LOOKING FORWARD TO SERVE YOU AGAIN\n";
             cout<<"\n			    HAVE A NICE DAY !\n\n";
             cout<<"=========================================================================\n"<<endl;
             }
             cin.get();
      }
      else
      {
            cout<<"**************************  YOUR ID IS WRONG  ***************************"<<endl;
                  ob1.customer_menu();
      }
}
//FUNCTION TO DISPLAY ALL THE PRODUCTS IN TABULAR FORM
void product::prod_tabular()
{
      int r=0,col=10;
      product st;
      ifstream inFile;
      inFile.open("product.csv",ios::binary);
      if(!inFile)
      {
            cout<<"File could not be open !! Press any Key..."<<endl;
            cin.get();
            return;
      }
      product_detail_heading();
      while(inFile.read((char *) &st, sizeof(product)))
      {
            if(r<=12)
            {
                  r++;
                  st.showall(col);
                  col++;
            }
            else
            {
                  cout<<"----------------------------- Press any key ----------------------------"<<endl;
                  cin.get();
                  product_detail_heading();
                  col=10;
                  r=0;
            }
      }
      inFile.close();
      cin.get();
}
//Function to display heading of the product details
void product::product_detail_heading()
{
      cout<<"========================================================================"<<endl;
      cout<<"*************************   PRODUCTS DETAILS   *************************"<<endl;
      cout<<"========================================================================"<<endl;
      cout<<"PROD.NO"<<setw(10)<<"NAME"<<setw(13)<<"COMPANY"<<setw(12)<<"PRICE"<<setw(13)<<"QUANTITY"<<endl;
      cout<<"------------------------------------------------------------------------"<<endl;
}
//FUNCTION TO MODIFY RECORD
void product::modify_record(int n)
{
      product st,temp;
      char tmpnm[50],tmpcompany[50];
      ifstream inFile;
      int fpos=-1;
      inFile.open("product.csv",ios::binary);
      if(!inFile)
      {
            cout<<"File could not be open !! Press any Key..."<<endl;
            cin.get();
            return;
      }
      int flag=0;
      while(inFile.read((char *) &st, sizeof(product)))
      {
            if(st.retpno()==n)
            {
                  st.show_prod();
                  flag=1;
            }
      }
      inFile.close();
      if(flag==0)
            cout<<"\n\nRecord doesnot exist"<<endl;
      else
      {
            fstream File;
            File.open("product.csv",ios::binary|ios::in|ios::out);
            if(!File)
            {
                  cout<<"File could not be open !! Press any Key..."<<endl;
                  cin.get();
                  return;
            }
            while(File.read((char *) &st, sizeof(product)))
            {
                  if(st.retpno()==n)
                  {
                        fpos=(int)File.tellg();
                        break;
                  }
            }
            File.seekp(fpos-sizeof(product),ios::beg);
            strcpy(tmpnm,st.getpname());
            strcpy(tmpcompany,st.getcompany());
            int q1=st.getqty();
            float pr=st.retprice();
            cout<<"===========   ENTER NEW VALUES FOR THE RECORDS GIVEN ABOVE   ==========="<<endl;
            temp.modifydata(n,tmpnm,pr,tmpcompany,q1);
            File.write((char *) &temp, sizeof(product));
            File.close();
      }
}
//FUNCTION TO DELETE THE RECORD OF THE PRODUCTS
void product::delete_record(int n)
{
      product st;
      ifstream inFile;
      inFile.open("product.csv",ios::binary);
      if(!inFile)
      {
            cout<<"File could not be open !! Press any Key..."<<endl;
            cin.get();
            return;
      }
      int flag=0;
      while(inFile.read((char *) &st, sizeof(product)))
      {
            if(st.retpno()==n)
            {
                  st.show_prod();
                  flag=1;
            }
      }
      inFile.close();
      char ch;
      if(flag==0)
            cout<<"\n\nRecord doesnot exist"<<endl;
      else
      {
            cout<<"DO YOU WANT TO DELETE THE RECORDS GIVEN ABOVE [YES(Y or y) OR NO(N or n)]"<<endl;
            cin>>ch;
            if (toupper(ch)=='Y')
            {
                  ofstream outFile;
                  outFile.open("Temp1.csv",ios::binary);
                  ifstream objiff("product.csv",ios::binary);
                  objiff.seekg(0,ios::beg);
                  while(objiff.read((char *) &st, sizeof(product)))
                  {
                        if(st.retpno()!=n)
                        {
                              outFile.write((char *) &st, sizeof(product));
                        }
                  }
                  outFile.close();
                  objiff.close();
                  remove("product.csv");
                  rename("Temp1.csv","product.csv");
                  againopenandclose();
                  cout<<"------------------------------Record Deleted----------------------------"<<endl;
            }
      }
      cin.get();
}
void againopenandclose()
{
      ifstream inFile;
      product st;
      inFile.open("product.csv",ios::binary);
      if(!inFile)
      {
            cin.get();
            return;
      }
      while(inFile.read((char *) &st, sizeof(product)))
      {
      }
      inFile.close();
}
//Fuction to search the product
int product::search(int p)
{
      product st;
      int tmprt=0;
      ifstream inFile;
      inFile.open("product.csv",ios::binary);
      if(!inFile)
      {
            cout<<"File could not be open !! Press any Key..."<<endl;
            cin.get();
            return -1;
      }
      int flag=0;
      while(inFile.read((char *) &st, sizeof(product)))
      {
            if(st.retpno()==p)
            {
                  st.show_prod();
                  flag=1;
                  tmprt=(int)inFile.tellg();
                  break;
            }
      }
      inFile.close();
      if(flag==0)
            return 1;
      else
      {
            return tmprt;
      }
}
//Function to change quantity of product
void product::changeqty(int pr1,int q11)
{
      product st;
      administrator ob;
      int fpos=-1;
      fstream File;
      File.open("product.csv",ios::binary|ios::in|ios::out);
      if(!File)
      {
            cout<<"File could not be open !! Press any Key..."<<endl;
            cin.get();
            return;
      }
      while(File.read((char *) &st, sizeof(product)))
      {
            if(st.retpno()==pr1)
            {
                  fpos=(int)File.tellg();
                  break;
            }
      }
      File.seekp(fpos-sizeof(product),ios::beg);
      int q1=st.getqty();
      q1=q1-q11;
      if(q1>0)
      {
        st.setqty(q1);
      }
      else
      {
            cout<<"Insufficient quantity !"<<endl;
            cout<<"Please reinitiate the billing process.Thank you."<<endl;
            ob.administratormenu();
      }
        File.write((char *) &st, sizeof(product));
      File.close();
}
//Fuction to copy all record to a structure
void copyme(int k2,order order1[50],int q1,int &c2)
{
      ifstream objiff2("product.csv",ios::binary);
      product bk1;
      objiff2.seekg(k2-sizeof(product));
      objiff2.read((char*)&bk1,sizeof(product));
      strcpy(order1[c2].pname1,bk1.getpname());
      strcpy(order1[c2].compy1,bk1.getcompany());

      order1[c2].price1=bk1.retprice();
      //COPY RECORD
      order1[c2].qty1=q1;
      c2++;
      objiff2.close();
}
// INTRODUCTION FUNCTION
void intro()
{
      cout<<endl;
      cout<<"========================================================================="<<endl;
      cout<<"-------------------------------------------------------------------------"<<endl;
      cout<<"****************  C A F E T E R I A   M A N A G E M E N T   *****************"<<endl;
      cout<<"-------------------------------------------------------------------------"<<endl;
      cout<<"***************************   S Y S T E M   *****************************"<<endl;
      cout<<"-------------------------------------------------------------------------"<<endl;
      cout<<"PROJECT BUILT BY : "<<endl;
      cout<<"                        MOUNBAGNA ABDELLA ABASSE                               "<<endl;
      cout<<"                        SALEEM AHMED ASSAN FAI                               "<<endl;
      cout<<"                        IBRAHIM NUHU                               "<<endl;
      cout<<"                        Guidado Oumar Alaouddini                                "<<endl;
      cout<<endl;
      cout<<"========================================================================="<<endl;
      cin.get();
}
// Customer Menu Function
void customer::customer_menu()
{
      char ch2;
      int num;
      administrator ob1;
      cout<<"\n==========================   CUSTOMERS MENU   =========================="<<endl;
      cout<<"1.CREATE CUSTOMERS DETAILS"<<endl;
      cout<<"2.DISPLAY ALL CUSTOMERS DETAILS"<<endl;
      cout<<"3.SEARCH RECORD OF A CUSTOMER "<<endl;
      cout<<"4.MODIFY CUSTOMERS RECORDS"<<endl;
      cout<<"5.DELETE CUSTOMERS RECORDS"<<endl;
      cout<<"6.BACK TO ADMINISTRATOR MAIN MENU"<<endl;
      cout<<"Please Enter Your Choice (1-6) "<<endl;
      cin>>ch2;
      switch(ch2)
      {
            case '1':
                  write_customer();
                  customer_menu();
                  break;
            case '2':
                  cust_tabular();
                  customer_menu();
                  break;
            case '3':
                  cout<<"ENTER THE CUST ID TO BE SEARCHED:"<<endl;
                  cin>>num;
                  display_cust_sp(num);
                  customer_menu();
                  break;
            case '4':
                  cust_tabular();
                  cout<<"\nENTER THE CUST ID TO BE MODIFIED:"<<endl;
                  cin>>num;
                  modify_cust_record(num);
                  customer_menu();
                  break;
            case '5':
                  cust_tabular();
                  cout<<"\nENTER THE CUST ID TO BE DELETED:"<<endl;
                  cin>>num;
                  deletecust_record(num);
                  customer_menu();
                  break;
            case '6':
                  ob1.administratormenu();
                  break;
            default:
                  cout<<"Please enter valid option"<<endl;
      }
}
// Product menu Function
void product::product_menu()
{
      char ch2;
      int num;
      administrator ob;
      cout<<"\n==========================   PRODUCTS MENU   ==========================="<<endl;
      cout<<"1.CREATE PRODUCTS"<<endl;
      cout<<"2.DISPLAY ALL PRODUCTS AVAILABLE"<<endl;
      cout<<"3.SEARCH RECORD (QUERY) "<<endl;
      cout<<"4.MODIFY PRODUCTS"<<endl;
      cout<<"5.DELETE PRODUCTS"<<endl;
      cout<<"6.BACK TO ADMINISTRATION MENU"<<endl;
      cout<<"Please Enter Your Choice (1-6) "<<endl;
      cin>>ch2;
      switch(ch2)
      {
            case '1':
                  write_book();
                  product_menu();
                  break;
            case '2':
                  prod_tabular();//product_detail_heading();
                  product_menu();
                  break;
            case '3':
                  cout<<"\nENTER THE PRODUCT ID TO BE SEARCHED:"<<endl;
                  cin>>num;
                  display_sp(num);
                  //search(num);
                  product_menu();
                  break;
            case '4':
                  prod_tabular();
                  cout<<"\nENTER THE PRODUCT ID TO BE MODIFIED:"<<endl;
                  cin>>num;
                  modify_record(num);
                  product_menu();
                  break;
            case '5':
                  prod_tabular();
                  cout<<"\nENTER THE PRODUCT ID TO BE DELETED:"<<endl;
                  cin>>num;
                  delete_record(num);
                  product_menu();
                  break;
            case '6':
                  ob.administratormenu();
                  break;
            default:

                  product_menu();
                  break;
      }
}

// Function for mainmenu
void customer::customermenu(){
     char ch;
     int num;
     customer ob;
     product ob1;
      do
      {
                  cout<<endl;
                  cout<<"-------------------------------------------------------------------------"<<endl;
                  cout<<"*********   C A N T E E N  M A N A G E M E N T  S Y S T E M   ***********"<<endl;
                  cout<<"-------------------------------------------------------------------------"<<endl;
                  cout<<endl;
            cout<<"=============================   MAIN MENU   ============================"<<endl;
            cout<<"1. PLACE ORDER"<<endl;
            cout<<"2. DISPLAY ALL FOOD AVAILABLE"<<endl;
            cout<<"3. SEARCH FOOD"<<endl;
            cout<<"4. BACK TO LOGIN MENU"<<endl;
            cout<<"5. EXIT"<<endl;
            cout<<"========================================================================"<<endl;
            cout<<"Please Select Your Option (1-3) "<<endl;
            cin>>ch;
            switch(ch)
            {
                  case '1':
                        orderk=0;
                         place_order();
                         customermenu();
                         break;
                  case '2':
                  ob1.prod_tabular();
                  ob.customermenu();
                  break;
                  case '3':
                  cout<<"\nENTER THE PRODUCT ID TO BE SEARCHED:"<<endl;
                  cin>>num;
                  ob1.display_sp(num);
                  //search(num);
                  customermenu();
                  break;
                  case '4':
                       ob.Login();
                       break;
                  case '5':
                        exit(0);
                  default :
                        cout<<"Please enter valid option"<<endl;
            }
      }while(ch!='3');
    }
void administrator::administratormenu()
{
      char ch;
      do
      {
            cout<<endl;
            cout<<"=========================   ADMINISTRATOR MENU   ======================="<<endl;
            cout<<"1. CUSTOMERS MENU"<<endl;
            cout<<"2. PRODUCTS MENU"<<endl;
            cout<<"3. BACK TO LOGIN MENU"<<endl;
            cout<<"========================================================================"<<endl;
            cout<<"Please Select Your Option (1-3) "<<endl;
            cin>>ch;
                  switch(ch)
                  {
                        case '1':
                              customer_menu();
                              break;
                        case '2':
                              product_menu();
                              break;
                        case '3':
                              Login();
                              break;
                        default :
                              cout<<"Please enter valid option"<<endl;
                  }
      }while(ch!='3');
}
int customer::Login()
{
      label:
    cout<<endl;
    cout<<"-------------------------------------------------------------------------"<<endl;
    cout<<"*********   C A F E T E R I A  M A N A G E M E N T  S Y S T E M   ***********"<<endl;
    cout<<"-------------------------------------------------------------------------"<<endl;
    cout<<endl;
      cout<<"1.REGISTER"<<endl;
      cout<<"2.LOGIN"<<endl;
      cout<<"3.EXIT"<<endl;
      string p,q,w;
      int x;string s;
      cin>>x;
      if(x==1)
      {
                  ofstream fout;
                  cout<<"ENTER YOUR USERNAME"<<endl;
                  cin>>s;
                  cout<<"ENTER YOUR PASSWORD"<<endl;
                  cin>>p;
                  s=s+p;
                  ifstream fin;
                  string line;
                  int offset=0;
                  fin.open("customerfile.txt");
                  if(fin.is_open())
                  {
                        while(!fin.eof())
                        {
                              getline(fin,line);
                              if(line.find(s,0)!=-1)
                              {
                                    cout<<"Sorry! This username is not available"<<endl;
                                    cout<<"Press any key to go to Home Page"<<endl;
                                    char kk;
                                    cin>>kk;
                                    goto label;
                                    //break;
                              }
                        }
                  }
                  fin.close();
                  fout.open("customerfile.txt",ios::app);
                  fout<<s+"\n";
                  fout.close();
                  goto label;
      }
      else if(x==2){
            string line;
            ifstream fin;
            int offset=0;
            cout<<"ENTER YOUR USERNAME"<<endl;
            cin>>q;
            cout<<"ENTER YOUR PASSWORD"<<endl;
            cin>>w;
            q=q+w;
            fin.open("customerfile.txt");
            if(fin.is_open())
            {
                  while(!fin.eof())
                  {
                        getline(fin,line);
                        if(line.find(q,0)!=-1)
                        {
                              cout<<"You are logged in !!"<<endl;
                              offset=1;
                                    customermenu();
                              break;
                }
                  }
                  if(offset==0)
                  {
                        cout<<"Sorry, You are unauthorised!!"<<endl;
                        cout<<"Press 1 and enter, to go to Home page"<<endl;
                        char o;
                        cin>>o;
                        goto label;
                  }
            }
            fin.close();
      }
      else if(x==3)
      {
            exit(0);
      }
      else
      {
          goto label;
      }
 }

 int administrator::Login()
{
      label:
          //administrator ob;
    cout<<endl;
    cout<<"-------------------------------------------------------------------------"<<endl;
    cout<<"*********   C A F E T E R I A  M A N A G E M E N T  S Y S T E M   ***********"<<endl;
    cout<<"-------------------------------------------------------------------------"<<endl;
    cout<<endl;
      cout<<"1.REGISTER"<<endl;
      cout<<"2.LOGIN"<<endl;

      cout<<"3.EXIT"<<endl;
      string p,q,w;
      int x;string s;
      cin>>x;
      if(x==1)
      {
                  ofstream fout;
                  cout<<"ENTER YOUR USERNAME"<<endl;
                  cin>>s;
                  cout<<"ENTER YOUR PASSWORD"<<endl;
                  cin>>p;
                  s=s+p;
                  ifstream fin;
                  string line;
                  int offset=0;
                  fin.open("administrator.txt");
                  if(fin.is_open())
                  {
                        while(!fin.eof())
                        {
                              getline(fin,line);
                              if(line.find(s,0)!=-1)
                              {
                                    cout<<"Sorry! This username is not available"<<endl;
                                    cout<<"Press any key to go to Home Page"<<endl;
                                    char kk;
                                    cin>>kk;
                                    goto label;
                                    //break;
                              }
                        }
                  }
                  fin.close();
                  fout.open("administrator.txt",ios::app);
                  fout<<s+"\n";
                  fout.close();
                  goto label;
      }
      else if(x==2){
            string line;
            ifstream fin;
            int offset=0;
            cout<<"ENTER YOUR USERNAME"<<endl;
            cin>>q;
            cout<<"ENTER YOUR PASSWORD"<<endl;
            cin>>w;
            q=q+w;
            fin.open("administrator.txt");
            if(fin.is_open())
            {
                  while(!fin.eof())
                  {
                        getline(fin,line);
                        if(line.find(q,0)!=-1)
                        {
                              cout<<"You are logged in !!"<<endl;
                              offset=1;
                                    administratormenu();
                              break;
                }
                  }
                  if(offset==0)
                  {
                        cout<<"Sorry, You are unauthorised!!"<<endl;
                        cout<<"Press 1 and enter, to go to Home page"<<endl;
                        char o;
                        cin>>o;
                        goto label;
                  }
            }
            fin.close();
      }
      else if(x=3)
      {
          exit(0);
      }
      else
      {
          goto label;
      }
 }
 int main()
{
    int choice;
    customer ob;
    administrator ob1;
      intro();
      cout<< "1. CUSTOMER MODE"<<endl;
      cout<< "2. ADMINISTRATOR"<<endl;

      cout<< "3. QUIT"<<endl;
      cout<< "Make a choice.";
      cin>>choice;

      switch(choice)
      {
      case 1:
        ob.Login();
        break;

      case 2:
        ob1.Login();
        break;
      case 4:
        cout<< "Goodbye..."<<endl;
        break;
      default:
        cout<< "Wrong choice...!!!"<<endl;
        exit(1);
      }

      return 0;
}


