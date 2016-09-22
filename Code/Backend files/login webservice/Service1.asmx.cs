using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Data;
using System.Data.SqlClient;
using MySql.Data;
using MySql.Data.MySqlClient;

namespace WebService2
{
    /// <summary>
    /// Summary description for Service1
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class Service1 : System.Web.Services.WebService
    {

        [WebMethod]
        public DataTable connectoToMySql()
        {
            string connString = "SERVER=MYSQL5002.Smarterasp.net" + ";" +
                "DATABASE=db_9ab3ca_pranu;" +
                "UID=9ab3ca_pranu;" +
                "PASSWORD=pintu848;" +
                "PORT=3306;";

            MySqlConnection cnMySQL = new MySqlConnection(connString);

            MySqlCommand cmdMySQL = cnMySQL.CreateCommand();

            MySqlDataReader reader;



            cmdMySQL.CommandText = "select * from faculty";

            cnMySQL.Open();

            reader = cmdMySQL.ExecuteReader();

            DataTable dt = new DataTable();
            dt.Load(reader);


            cnMySQL.Close();

            return dt;
        }

        [WebMethod]
        public string faculty(string username, string password)
        {
            string connString = "SERVER=MYSQL5002.Smarterasp.net" + ";" +
                 "DATABASE=db_9ab3ca_pranu;" +
                 "UID=9ab3ca_pranu;" +
                 "PASSWORD=pintu848;" +
                 "PORT=3306;";

            MySqlConnection cnMySQL = new MySqlConnection(connString);

            MySqlCommand cmdMySQL = cnMySQL.CreateCommand();

            MySqlDataReader reader;

            cmdMySQL.CommandText = "select * from faculty WHERE username='" + username + "' AND password='" + password + "'";

            cnMySQL.Open();

            reader = cmdMySQL.ExecuteReader();



            DataTable dt = new DataTable();
            dt.Load(reader);
            Object o = dt.Rows[0]["username"];

            Object i = dt.Rows[0]["password"];


            if (o == username && i == password)
            {
                return "unsuccess";
            }
            else
            {
                return "success";
            }

            cnMySQL.Close();


        } 
    


    }
}