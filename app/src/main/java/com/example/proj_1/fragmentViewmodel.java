package com.example.proj_1;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class fragmentViewmodel extends ViewModel {


    public MutableLiveData<String> mweather,mname,mage,mweight,mheight;
    public MutableLiveData<String>  mtempmax,mtempmin,mtarget,mwaterd,mfruitd,mcoffe,menergyd,mwine,mbeer;
    public MutableLiveData<String> waterdrink;





    public void  setMweather(String s) { mweather.setValue(s); }
    public  void setMtempmax(String f)
    {
        mtempmax.setValue(f);
    }
    public void setMtempmin(String f)
    {
        mtempmin.setValue(f);
    }
    public void setMtarget(String s){mtarget.setValue(s);}
    public void setMwaterd(String s ){mwaterd.setValue(s);}

    public void setWaterdrink(String x)
    {
        waterdrink.setValue(x);
    }

    public void setMfruitd(String x){
        mfruitd.setValue(x);
    }
    public void setMcoffe(String x){
      mcoffe.setValue(x);
    }
    public void setMenergyd(String x){
       menergyd.setValue(x);
    }
    public void setMwine(String x)  {
        mwine.setValue(x);
    }
    public void setMbeer(String x)  {
        mbeer.setValue(x);
    }

    public void setMname(String s)  {mname.setValue(s);}
    public void setMage(String s)   {mage.setValue(s);}
    public void setMweight(String s)    {mweight.setValue(s);}
    public void setMheight(String s)    {mheight.setValue(s);}


    public LiveData<String> getmweather(){
        if(mweather==null)
        {
            mweather=new MutableLiveData<String>();
            setMweather("none");
        }
       return mweather;
    }


    public LiveData<String> gettempmax()
    {
        if(mtempmax==null)
        {
            mtempmax=new MutableLiveData<String>();
            setMtempmax("0");
        }
        return mtempmax;
    }
    public LiveData<String> gettempmin()
    {
        if(mtempmin==null)
        {
            mtempmin=new MutableLiveData<String>();
            setMtempmin("0");
        }
        return mtempmin;
    }
    public LiveData<String> getMtarget()
    {
        if(mtarget==null)
        {
            mtarget=new MutableLiveData<String>();
            setMtarget("0");
        }
        return mtarget;
    }

     public LiveData<String> getMwaterd()
    {
        if(mwaterd==null)
        {
            mwaterd=new MutableLiveData<String>();
            setMwaterd("0");
        }
        return mwaterd ;
    }
     public LiveData<String> getMfruitd()
    {
        if(mfruitd==null)
        {
            mfruitd=new MutableLiveData<String>();
            setMfruitd("0");
        }
        return mfruitd;
    }
      public LiveData<String> getMcoffe()
    {
        if(mcoffe==null)
        {
            mcoffe=new MutableLiveData<String>();
            setMcoffe("0");
        }

        return mcoffe;
    }
      public LiveData<String> getMenergyd()
    {
        if(menergyd==null)
        {
            menergyd=new MutableLiveData<String>();
            setMenergyd("0");
        }
        return menergyd;
    }
      public LiveData<String> getMwine()
    {
        if(mwine==null)
        {
            mwine=new MutableLiveData<String>();
            setMwine("0");
        }
        return mwine;
    }
    public LiveData<String> getMbeer()
    {
        if(mbeer==null)
        {
            mbeer=new MutableLiveData<String>();
            setMbeer("0");
        }
        return mbeer;
    }
    public LiveData<String> getMname()
    {
        if(mname==null)
        {
            mname=new MutableLiveData<String>();
            setMname("Prajwal");
        }
        return mname;
    }
    public LiveData<String> getMage()
    {
        if(mage==null)
        {
            mage=new MutableLiveData<String>();
            setMage("21 yrs");
        }
        return mage;
    }
    public LiveData<String> getMweight()
    {
        if(mweight==null)
        {
            mweight=new MutableLiveData<String>();
            setMweight("58 kg");
        }
        return mweight;
    }
    public LiveData<String> getMheight()
    {
        if(mheight==null)
        {
            mheight=new MutableLiveData<String>();
            setMheight("181 cm");
        }
        return mheight;
    }

    public LiveData<String> getWaterdrink()
    {
        if(waterdrink==null)
        {
            waterdrink=new MutableLiveData<String>();
            setWaterdrink("0");
        }
        return waterdrink;
    }

    public int getTarget()
    {

        int target=2300;
        target+=Math.random()%800;
        return target;
    }

}

//      public LiveData<String> ()
//    {
//        if(==null)
//        {
//            =new MutableLiveData<String>();
//            set("0");
//        }
//        return ;
//    }

