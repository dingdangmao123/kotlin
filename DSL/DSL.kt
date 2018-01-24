import java.net.URL

/**
 * Created by suxiaohui on 2018/1/21.
 */


fun main(args: Array<String>) {

    App {
        url="http://home.firefoxchina.cn/"
        type="url"
    }


}


class upload{

    var url=""

    var type=""

    var success:(res:MutableList<String>)->Unit={res->
        for(i in res)
         println(i)
    }

    var error:(e:Exception)->Unit={e->
        println(e.toString())
    }

    fun run(){

        when(type){
                "url"->getUrl()

        }

    }

    fun getUrl(){

        try {
            var url = URL(this.url)
            var text = url.readText()
            var m = Regex("href=\"(http|https)://([^\"]+)\"").findAll(text)
            var res = mutableListOf<String>()
            for (i in m) {
                res.add(i.groupValues[2])
            }
            success(res)

        }catch(e:Exception){
            error(e)
        }

    }
}

fun App(config:upload.()->Unit){
    var ins=upload()
    ins.config()
    ins.run()
}