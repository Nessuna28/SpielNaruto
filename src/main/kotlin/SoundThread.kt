import java.io.File
import javax.sound.sampled.AudioSystem

// Idee von mir
// Code von Olli
class SoundThread(var file: String): Thread() {

    var isPlaying = false

    override fun run() {

        while (!isPlaying) {
            playSound()
        }
    }

    // spielt Sound ab
    fun playSound() {

        if (!isPlaying) {
            val file = File("./$file")
            val audio = AudioSystem.getAudioInputStream(file.toURI().toURL())
            val clip = AudioSystem.getClip()
            clip.open(audio)
            clip.start()
            isPlaying = true
        }
    }

    fun stopPlaying(){

        isPlaying = false
    }
}