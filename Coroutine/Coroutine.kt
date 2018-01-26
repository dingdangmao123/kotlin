import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import java.io.File
import java.net.URL

/**
 * Created by suxiaohui on 2018/1/21.
 */


fun main(args: Array<String>)=runBlocking {

    var num=50
    var cache=100
    var dir="G:\\Hub\\wallpaper"
    var page=21

    var ch=Channel<String>(cache)
    var job= mutableListOf<Job>()

    for(i in 1..num)
        job.add(launch(CommonPool) {
            for(i in ch)
                File(dir+File.separator+i.split("/").last()).writeBytes(URL(i).readBytes())
            })
    var count=0
    for(i in 1..page) {
        var j=(i-1)*24
        var pos="https://www.vladstudio.com/zh/wallpapers/?skip=$j"
        println(pos)
        var p="<img class=\"framed\" src=\"(http[^\"]+)\""
        for (j in Regex(p).findAll(URL(pos).readText())) {
            count++
            ch.send(j.groupValues.get(1).replace("480x320","1920x1080"))
        }
    }
    ch.close()
    println("total: "+count)
    for(i in job)
        i.join()
    println("done")
}




