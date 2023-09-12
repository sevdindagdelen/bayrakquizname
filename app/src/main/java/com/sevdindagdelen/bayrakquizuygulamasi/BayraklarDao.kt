package com.sevdindagdelen.bayrakquizuygulamasi

class BayraklarDao {

    fun rastgele5bayrakGetir(vt:VeriTabaniYardimcisi):ArrayList<Bayraklar> {

        val bayrakArraylist = ArrayList<Bayraklar>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 5", null)

        val bayrakIdIndex = c.getColumnIndex("bayrak_id")
        val bayrakAdIndex = c.getColumnIndex("bayrak_ad")
        val bayrakResimIndex = c.getColumnIndex("bayrak_resim")

        while (c.moveToNext()) {
            val bayrak = Bayraklar(
                c.getInt(bayrakIdIndex),
                c.getString(bayrakAdIndex),
                c.getString(bayrakResimIndex)
            )
            bayrakArraylist.add(bayrak)
        }
        return bayrakArraylist
    }

       /** while (c.moveToNext()){
            val bayrak=Bayraklar(c.getInt(c.getColumnIndex("bayrak_id")),
                c.getString(c.getColumnIndex("bayrak_ad")),
                c.getString(c.getColumnIndex("bayrak_resim")))
            bayrakArraylist.add(bayrak)
        }
        return bayrakArraylist
    } */

    fun rastgele3YanlisbayrakGetir(vt:VeriTabaniYardimcisi,bayrak_id:Int):ArrayList<Bayraklar>{

        val bayrakArraylist=ArrayList<Bayraklar>()
        val db=vt.writableDatabase
        val c=db.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id !=$bayrak_id ORDER BY RANDOM() LIMIT 3",null)

           val bayrakIdIndex = c.getColumnIndex("bayrak_id")
           val bayrakAdIndex = c.getColumnIndex("bayrak_ad")
           val bayrakResimIndex = c.getColumnIndex("bayrak_resim")

           while (c.moveToNext()) {
               val bayrak = Bayraklar(
                   c.getInt(bayrakIdIndex),
                   c.getString(bayrakAdIndex),
                   c.getString(bayrakResimIndex)
               )
               bayrakArraylist.add(bayrak)
           }
           return bayrakArraylist
    }
}