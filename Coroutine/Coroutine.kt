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

    var num=30
    var cache=100
    var dir="G:\\Hub"
    var page=10

    var ch=Channel<String>(cache)
    var job= mutableListOf<Job>()


    for(i in 1..num)
        job.add(launch(CommonPool) {
            for(i in ch)
                File(dir+File.separator+i.split("/").last()).writeBytes(URL(i).readBytes())
            })

    var count=0
    for(i in 1..page) {
        var pos="https://pixabay.com/zh/photos/?order=ec&pagi=$i"
        println(pos)
        for (j in Regex("src=\"(http[^\"]+\\.(png|jpg|jpeg))\"").findAll(URL(pos).readText())) {
            count++
            ch.send(j.groupValues.get(1))
        }
        println(count)
    }

    ch.close()
    println(count)
    for(i in job)
        i.join()

    println("done")
}




