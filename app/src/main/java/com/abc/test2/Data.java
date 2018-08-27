package com.abc.test2;

public class Data {

    public  Result  result;

    public class Result{

        public Records[] records;

        public void setRecords(Records[] records) {
            this.records = records;
        }

        public Records[] getRecords() {
            return records;
        }

        public class Records{

           public String Website;
            public String Changetime;
            public String Ticketinfo;
            public String Opentime;
            public String Tel;
            public String Name;
            public String Px;
            public String Py;
            public String Toldescribe;
            public String InfoId;
            public String Zipcode;
            public String  Add;
            public String  Fax;
            public String Remarks;
            public int  _id;
            public String Parkinginfo;
            public String TYWebsite;
        }
    }
}
