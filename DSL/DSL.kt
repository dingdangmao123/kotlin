import java.net.URL

/**
 * Created by suxiaohui on 2018/1/21.
 */


fun main(args: Array<String>) {



}


class upload{

    var url=""

    var type=""

    var success:()->Unit={

        println("success")
    }


    var error:()->Unit={

        println("error")

    }

    fun run(){

        when(type){

            



        }


    }

    fun getUrl():MutableList<String>{

        var url=URL(this.url)
        var text=url.readText()
        var m=Regex("href=\"([^\"]+)\"").findAll(text)
        var res= mutableListOf<String>()
        for(i in m){
            res.add(i.groupValues[1])
        }
        return res
    }
}

fun App(config:upload.()->Unit){
    var ins=upload()
    ins.config()
    ins.run()
}