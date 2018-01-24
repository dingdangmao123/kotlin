import java.io.File
import java.net.URL

/**
 * Created by suxiaohui on 2018/1/21.
 */


fun main(args: Array<String>) {

    App {
        
        url="http://home.firefoxchina.cn/"
        
        type="url"
    }

    App {
        url="https://pic2.zhimg.com/80/v2-0eae439b327af015a06ebc986918354b_hd.jpg"

        type="Pic"

        dir="G:\\Hub"

        name="demo"
    }

}

class upload{

    var url=""

    var type=""

    var dir=""

    var name=""

    var successUrl:(res:MutableList<String>)->Unit={res->
        for(i in res)
         println(i)
    }

    var successPic:(url:String)->Unit={str->
        println(url+" download success!")
    }

    var error:(e:Exception)->Unit={e->
        println(e.toString())
    }

    fun run(){
        when(type){
            "url"->getUrl()
            "Pic"->getPic()
            else->println("arg error")
        }
    }
    fun getPic(){

        if(dir==""){
            println("file is empty")
            return
        }

        try {
            var url = URL(this.url)
            var byte = url.readBytes()
            File(this.dir+File.separator+this.name+"."+this.url.split(".").last()).writeBytes(byte)
            successPic(this.url)
        }catch(e:Exception){
            error(e)
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
            successUrl(res)

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