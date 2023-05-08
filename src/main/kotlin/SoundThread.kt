import java.io.File
import javax.sound.sampled.AudioSystem

class SoundThread(var file: String): Thread() {

    override fun run() {

        playSound()

    }

    // spielt Sound ab
    fun playSound() {

        val file = File("./$file")
        val audio = AudioSystem.getAudioInputStream(file.toURI().toURL())
        val clip = AudioSystem.getClip()
        clip.open(audio)
        clip.start()
    }
}