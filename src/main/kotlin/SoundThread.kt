import java.io.File
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

// Idee von mir
// Code von Olli
class SoundThread(var file: String): Thread() {

    var clip: Clip = AudioSystem.getClip()

    override fun run() {

        playSound()
    }

    // spielt Sound ab
    fun playSound() {

            val file = File("./$file")
            val audio = AudioSystem.getAudioInputStream(file.toURI().toURL())
            clip = AudioSystem.getClip()
            clip.open(audio)
            clip.start()
    }

    fun stopPlaying(){

        clip.stop()
    }
}