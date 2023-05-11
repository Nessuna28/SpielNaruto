import Charakters.*
import Fights.*

val reset = "\u001b[0m"
val magenta = "\u001b[35m"
val blue = "\u001b[34m"
val green = "\u001b[32m"
val red = "\u001b[31m"
val yellow = "\u001b[33m"
val cyan = "\u001b[36m"
val greyBackground = "\u001b[47m"
val greenBackground = "\u001b[42m"
val blueBackground = "\u001b[44m"


// diese Variablen sind außerhalb der Main, sodass ich von überall etwas in ihnen abspeichern kann
// und nach Belieben aufrufen kann ohne ständig zwischendurch viele Variablen anlegen zu müssen
var nameUser = ""
var favoriteColorUser = ""
var selectionUserString = ""
var selectionUserInt = 0
var characterUser = Character("", mutableMapOf(), mutableMapOf(), mutableMapOf())
var teamUser = mutableListOf<Character>()
var characterComputer = Character("", mutableMapOf(), mutableMapOf(), mutableMapOf())
var teamComputer = mutableListOf<Character>()
var selectionComputer = ""
var lifePointsUser = 500
var lifePointsComputer = 500
val soundThread = SoundThread("sounds/themeSong.wav")

var counterRounds = 0
var counterWins = 0


fun main() {

    val game = Thread {
        //soundThread.file = "sounds/beginnSong.wav"
       //soundThread.start()

        greeting()

        do {
            selectionTeamOrCharacter()
            useSong()
            soundThread.start()
            characterComputer()
            valueOfCharacterPrint()
            do {
                selectionAttackUser()
                selectionAttackTeamUser()
                grafikForAttack()
                attackComputer()
                attackComputerTeam()
                lostLifePointsSinglePlay(selectionUserString, selectionComputer, characterUser, characterComputer)
                lostLifePointsTeamPlay(selectionUserString, selectionComputer, mainCharacterUser, mainCharacterComputer)
                defensePrint()
                wichAttackUserPrint()
                whichAttackComputerPrint()
                valueOfCharacterPrint()
            } while (characterComputer.lifePoints > 0 && characterUser.lifePoints > 0)

            winOrLosePrint()
            newRoundOrNotAndCountRoundsWon()
        } while (selectionUserString == "ja")
    }

    game.start()

}

// der Spieler wird begrüßt und danach werden die weiteren Funktionen aufgerufen
fun greeting() {

    println("$blue                        Willkommen im Spiel")
    Thread.sleep(1000)
    println("""
        
                                 _/      _/                                  _/               
                                _/_/    _/    _/_/_/  _/  _/_/  _/    _/  _/_/_/_/    _/_/    
                               _/  _/  _/  _/    _/  _/_/      _/    _/    _/      _/    _/   
                              _/    _/_/  _/    _/  _/        _/    _/    _/      _/    _/    
                             _/      _/    _/_/_/  _/          _/_/_/      _/_/    _/_/       
                                                           
    """.trimIndent())
    Thread.sleep(2000)
    println("""
        
                      ⬜⬜⬜⬜⬜⬜🟧⬜⬜⬜🟧⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬜🟧🟧⬜⬜🟧🟧⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜🟧⬜🟧🟧⬜🟧🟧🟧⬜🟧⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜🟧🟧🟧🟧🟨🟧🟧⬜🟨🟧🟧🟧🟧🟧⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      🟧⬜🟧🟧🟧🟨🟨🟧🟨⬜🟧🟧⬜🟧🟧⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      🟧🟧🟧🟧🟧🟨🟧🟧🟨🟨🟨⬜🟧🟧⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      🟧🟧🟧🟧🟧🟧🟧🟨🟨🟨🟨🟧🟧🟧🟧🟧⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      🟧⬜🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟧🟧🟧⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      🟧🟦🟦🟪🟪⬜⬜🟪🟦🟦🟦🟦🟦🟦🟦⬛⬛⬛⬛⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      🟧🟦🟪⬜🟪⬜⬜🟪🟦🟦🟦🟦🟦🟦🟦🟦🟦🟧🟧🟧⬛⬛⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      ⬜🟦⬜⬜🟪🟦🟦🟦🟦🟦🟦🟨🏽🟦🟦🟦🟧🟧🟧⬛🟧🟧🟧⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      ⬜🟦🟦🟦🟦🏼🏽⬛⬜🏽🏼🏽🏻🏽🟦🟦🟦🟦🟧🟧🟧🟦⬛⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬛🟦🏼🏻🏼🟦⬜🏼🏻🏽🏼⬛🟧🟧🟦🟧⬛🟧🟦⬛🏼🏻🏽⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬛🏼🏻🏻🏻🏻🏻🏻🏼⬛⬛⬜⬛🟧🟧⬛⬛🟦🟦🏼⬛🏽🏻🏽⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬛🏼🏽🏼🏻🏻🏻🏻🏽⬛⬜⬜⬛🟧⬛⬜🟦🟦🏼🟦🟦🟦🟦🏼🏽⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬛🏼🏻⬜🏽🏼🏽⬛🟧🟪⬜🟦⬛⬜⬜🟦🟦🟦🟦🟦🟦🟦🟦🟦⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬛🏼🏼🏼🏽⬛🟧🟧🟧🟪🟧⬛⬜⬜🟦🟦🟦⬜⬜⬜⬜🟦🟦🟦⬜⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬛⬛⬛⬛⬛🟪⬜🟧🟧⬛🟧⬛⬛⬜🟦🟦⬜⬜⬜⬜⬜⬜🟦🟦⬜⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬛🟪⬜⬜⬜⬜🟪🟦🟧🟧🟧🟧⬛🟦🟦⬜⬜🟦🟦🟦🟦⬜🟦🟦🟦⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬛🟪🟦🟦🟦🟪⬛🟧⬛🟧🟧⬛🟦🟦🟦⬜⬜🟦⬜⬜🟦⬜🟦🟦🟦⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬜⬛🟦🟪🟦🟧🟧⬛🟧⬛⬛⬛⬛🟦🟦⬜⬜🟦⬜⬜🟦🟦🟦🟦🟦⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬜⬛🟪⬜🟪🟧🟧🟧⬛🟦⬛🟧⬛🟦🟦⬜⬜🟦🟦🟦🟦🟦🟦🟦🟦⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬜⬜⬛⬜⬜🟧🟧🟧🟧⬛🟧🟧⬛🏼🟦🟦⬜⬜⬜⬜🟦🟦🟦🟦⬜⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬜⬜⬜⬛🟧🟧🟧🟦🟧🟧🟧🟧⬛🏽🟦🟦🟦⬜⬜⬜🟦🟦🟦🟦⬜⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛🟧🟧🟧🟧🟧🟧🟧🟧⬛🟦🟦🟦🟦🟦🟦🟦🟦⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬜⬜⬛🟧🟧🟧⬛🟧🟧🟧🟧🟧🟧🟧🟧🟧🟦🟦🟦🟦🟦🟦⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬜⬛🟧🟧🟧🟧🟧⬛🟧🟧🟧⬛🟧🟧⬛🟦⬜⬜🟧🟦🟦🟦⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬛🟧🟧🟧🟧🟧🟧⬛⬛⬛⬛⬛⬛⬛🟦⬜🟧🟧🟧🟧🟦⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬛🟧🟧🟧🟧🟧⬛⬛⬛⬛⬜⬜⬛⬛🟦🟧🟧🟧🟧🟦🟧🟧⬛⬛⬛⬜⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬛🟧🟧🟧⬛⬛🟧⬛🟧⬛⬜⬜⬜⬜⬛⬛🟧🟧🟧🟧🟧🟧🟧🟧🟧⬛⬜⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬛🟧🟧🟧🟧🟧🟧⬛🟧⬛⬛⬜⬜⬜⬜⬜⬛🟧🟧🟧🟧🟧🟧🟧⬛🟧⬛⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬜⬛🟧🟧🟧🟧⬛🟧🟧⬛🟦⬛⬜⬜⬜⬜⬜⬛⬛🟧🟧🟧🟧🟧⬛🟧⬛⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬜⬜⬛⬛⬛⬛🟧🟧⬛🟦🟦⬛⬜⬜⬜⬜⬜⬜⬜⬛🟧🟧⬛⬛🟧🟧⬛⬜⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬛🟦🟦🟦⬛⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛🟧🟧🟧⬛⬛⬛⬜⬜⬜⬜
                      ⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛🟦🟦🟦⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛🟦⬛⬛⬛⬜⬜⬜
                      ⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛🟦🟦🟦⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜🟦🟦🟦🟦🟦⬛⬜⬜
                      ⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛🟦🟦⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜🟦🟦🟦🟦🟦⬛⬜
                      ⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛🏻🏻⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛🟦🟦🟦🟦⬛⬛
                      ⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛🏻🏻🏽🏻🏼⬛
                      ⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬛⬛⬛$reset
    """.trimIndent())

    playerNameUser()

    selectionFavoriteColorUser()

   askListenRules()
}

// der Spieler wird aufgefordert einen Spielernamen einzugeben
fun playerNameUser() {

    Thread.sleep(2000)
    print("\nWie ist dein Spielername? : ")
    nameUser = readln()
}

// der Spieler wird nach seiner Lieblingsfarbe gefragt
// diese wird dann gespeichert und eingesetzt als Akzentfarbe für die Ausgabe seiner Daten
fun selectionFavoriteColorUser() {

    var check = false

    while (!check) {
        try {
            println(
                """
        
        Welche ist deine Lieblingsfarbe?
        ${red}rot$reset, ${green}grün$reset, ${yellow}gelb$reset, ${blue}blau$reset, ${magenta}magenta$reset, ${cyan}cyan $reset
    """.trimIndent()
            )
            print("Wähle deine Farbe! : ")
            val color = readln().lowercase()

            if (color != "rot" && color != "grün" && color != "gelb" && color != "blau" && color != "magenta" && color != "cyan") {
                println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                continue
            } else {
                when (color) {
                    "rot" -> favoriteColorUser = red
                    "grün" -> favoriteColorUser = green
                    "gelb" -> favoriteColorUser = yellow
                    "blau" -> favoriteColorUser = blue
                    "magenta" -> favoriteColorUser = magenta
                    "cyan" -> favoriteColorUser = cyan
                }
                check = true
            }
        } catch (ex: Exception) {
            println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
        }
    }
}

// der Spieler wird gefragt, ob er die Regeln hören möchte, falls ja, werden sie ihn über die Funktion rules angezeigt
fun askListenRules() {

    Thread.sleep(1000)

    var check = false

    while (!check) {
        try {
            print("\nHallo $favoriteColorUser${nameUser}$reset, möchtest du dir die Regeln anzeigen lassen? \nWähle 'ja' oder 'nein' : ")
            selectionUserString = readln().lowercase()

            if (selectionUserString == "ja")
                rules()
            else if (selectionUserString == "nein") {
                println("\n\uD83D\uDC4D\uD83C\uDFFC Gut du kennst dich also aus. Dann ab zum Spiel!")
                check = true
            } else
                println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")

        } catch (ex: Exception) {
            println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
        }
    }
}

// die Regeln des Spiels
fun rules() {

    println(
        """
        Du hast die Wahl mit einem Einzelcharakter oder einem Team zu spielen.
       
        Einzelkampf:
        
        Hast du nur einen einzelnen Charakter greifst du auch nur einmal mit dem an und danach ist der Gegner dran.
        Du hast mehrer Möglichkeiten, entweder angreifen oder mit dem 'Baumstamm' auszuweichen.
        
        Entscheidest du dich für den Angriff kannst du wählen ob mit Thaijutsu, Ninjutsu oder einer Waffe und je nach 
        Charakter noch die Möglichkeit einen Bijuu oder Susanoo zu erwecken oder sich zu heilen.
        In den ersten drei Kategorien hast du dann noch mehrere Attacken zur Auswahl.
        Jede Attacke verursacht einen gewissen Schaden, die eine mehr, die andere weniger und der Gegner verliert Lebenspunkte.
        Entscheidest du dich für ein Ninjutsu wird dir Chakra abgezogen auch wenn der Gegner ausgewichen ist.
        
        Entscheidest du dich für Baumstamm werden dir natürlich keine Lebenspunkte abgezogen und dein Chakra füllt sich wieder um 
        10 Punkte auf, genau wie bei allen anderen Attacken die kein Chakra benötigen.
        
        Teamkampf:
        
        Hast du dich für ein Team entschieden, wirst du gefragt welcher Charakter aus dem Team dein Hauptcharakter sein soll und
        dann kämpfst du mit diesem so wie bei einem Einzelkampf, nur hast du jetzt die zusätzliche Option dein Team um Hilfe zu bitten.
        
        Das bedeutet das die beiden anderen Charaktere eine zufällige Attacke ausführen und den Gegner angreifen.
        Diese zufälligen Attacken sind aber auf Thaijutsu, Ninjutsu, Waffen und Heilung beschränkt. Das heißt es kann kein
        Bijuu oder Susanoo erweckt werden und kein Genjutsu eingesetzt werden.
        
        Ist die zufällige Attacke Heilung, heilt das Teammitglied den Hauptcharakter und nicht sich selbst.
        
        Alles andere bleibt wie bei einem Einzelkampf.
        
        
        Wer zuerst keine Lebenspunkte mehr hat verliert dieses Spiel. ${favoriteColorUser}Viel Spaß! $reset
        
    """.trimIndent()
    )
}

// der Spieler hat die Möglichkeit zu wählen, ob er mit einem Team spielen möchte oder mit einem einzelnen Charakter
// sofern die Eingabe nicht falsch ist, wird gleich die nächste Funktion je nach Eingabe ausgeführt
fun selectionTeamOrCharacter() {

    var check = false

    Thread.sleep(1000)

    while (!check) {
        try {
            print("\nWählst du ein Team oder einen einzelnen Charakter? \nWähle 'Team' oder 'Einzel'. : ")
            selectionUserString = readln().lowercase()

            if (selectionUserString == "team" || selectionUserString == "einzel") {
                selectionSelfOrRandom()
                check = true
            } else {
                println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
            }
        } catch (ex: Exception) {
            println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
        }
    }
}

// der Spieler hat die Möglichkeit selbst zu wählen oder per Zufallsgenerator
// je nach Eingabe wird die nächste Funktion ausgeführt
fun selectionSelfOrRandom() {

    var check = false
    val inputUserTeamOrCharacter = selectionUserString

    while (!check) {
        try {
            print("\nMöchtest du selbst wählen oder per Zufallsgenerator? \n Wähle 'selbst' oder 'Zufall'. : ")
            selectionUserString = readln().lowercase()

            if (selectionUserString == "selbst") {
                println("\nDu hast dich entschieden selbst zu wählen. \n")
                if (inputUserTeamOrCharacter == "einzel") {
                    selectionCharacter()
                    break
                } else if (inputUserTeamOrCharacter == "team") {
                    selectionTeam()
                    break
                }
                check = true
            } else if (selectionUserString == "zufall") {
                println("\nDu hast dich für den Zufallsgenerator entschieden. \n")
                if (inputUserTeamOrCharacter == "einzel") {
                    randomGeneratorForOneCharacter()
                    break
                }else if (inputUserTeamOrCharacter == "team"){
                    randomGeneratorForTeam()
                    break
                }
                check = true
            } else {
                println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
            }
        } catch (ex: Exception) {
            println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
        }
    }
}

// dem Spieler werden die vorhandenen Charaktere angezeigt und er darf sich, per Eingabe, einen aussuchen
// Spieler hat drei Versuche für die Eingabe
// bei drei falschen Eingaben, wird dem Spieler per Zufallsgenerator ein Spieler ausgesucht
fun selectionCharacter() {

    var counter = 0

    println(
        """
            Die Charaktere die du zur Auswahl hast sind: $favoriteColorUser
            ${characterNameList.slice(0..5)}
            ${characterNameList.slice(6..11)}
            ${characterNameList.slice(12..18)}
            ${characterNameList.slice(19..21)}
            ${characterNameList.slice(22..29)}
            $reset
        """.trimIndent()
    )

    while (counter < 3) {
        try {
            print("Für welchen Charakter entscheidest du dich? Gib den Namen ein: ")
            selectionUserString = readln().lowercase()

            val lowercaseList = listToLowercaselist(characterNameList)

            if (lowercaseList.contains(selectionUserString)) {
                println("\nSuper! Du hast dich für $favoriteColorUser${selectionUserString.uppercase()} ${reset}entschieden.")
                setCharacterForUser(selectionUserString)
                Thread.sleep(1200)
                grafik(selectionUserString)
                break
            } else {
                println("\n❌ Du hast eine falsche Auswahl getroffen.")
                counter++
            }
        } catch (ex: Exception) {
            println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
        }

    }

    if (counter == 3) {
        println("\nDa du keine richtige Auswahl getroffen hast, wird dir ein zufälliger Charakter zugewiesen.")
        randomGeneratorForOneCharacter()
    }
}

// dem Spieler werden die vorhandenen Charaktere angezeigt und er darf sich, per Eingabe, drei Charaktere aussuchen
// auch hier wieder drei Versuche für die Eingabe
// bei drei falschen Eingaben, werden dem Spieler per Zufallsgenerator drei Spieler ausgesucht
fun selectionTeam(){

    var counter = 0

    println(
        """
                
            Die Charaktere die du zur Auswahl hast sind: $blue
            ${characterNameList.slice(0..5)}
            ${characterNameList.slice(6..11)}
            ${characterNameList.slice(12..18)}
            ${characterNameList.slice(19..21)}
            ${characterNameList.slice(22..29)}
            $reset
        """.trimIndent()
    )

    while (counter < 3) {
        try {
            print("Für welche Charaktere entscheidest du dich? Gib drei Namen ein und trenne sie mit Komma und Leerzeichen: ")
            selectionUserString = readln().lowercase()
            val inputList = selectionUserString.split(", ")

            val lowercaseList = listToLowercaselist(characterNameList)

            if (inputList[0] in lowercaseList && inputList[1] in lowercaseList && inputList[2] in lowercaseList) {
                println("\nSuper! Du hast dich für $favoriteColorUser${selectionUserString.uppercase()} ${reset}entschieden.")

                for (character in inputList){
                    setCharacterForUser(character)
                    teamUser.add(characterUser)
                }
                counter = 4
            } else {
                println("\n❌ Du hast eine falsche Auswahl getroffen.")
                counter++
            }
        } catch (ex: Exception) {
            println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
        }


        if (counter == 3) {
            println("Da du keine richtige Auswahl getroffen hast werden dir 3 zufällige Charaktere zugewiesen.")
            randomGeneratorForTeam()
        }
    }

    selectionMainCharacter()
    characterUser.name = ""
}

// der Zufallsgenerator für einen Charakter
fun randomGeneratorForOneCharacter() {

    val characterForRandom = characterNameList.random()

    Thread.sleep(2000)

    println("Dein Charakter ist $favoriteColorUser${characterForRandom.uppercase()} $reset")
    setCharacterForUser(characterForRandom.lowercase())
    grafik(characterForRandom.lowercase())
}

// der Zufallsgenerator für drei Charaktere
// es kommt kein Charakter doppelt in einem Team vor
fun randomGeneratorForTeam(){

    val listOfCharactersForRandom = mutableListOf<String>()

    while (listOfCharactersForRandom.size < 3) {
        val randomCharacter = characterNameList.random()
        if (!listOfCharactersForRandom.contains(randomCharacter)) {
            listOfCharactersForRandom.add(randomCharacter)
            setCharacterForUser(randomCharacter.lowercase())
            teamUser.add(characterUser)
        }
    }
    Thread.sleep(2000)
    println("Deine Charaktere sind: $favoriteColorUser${listOfCharactersForRandom.toString().uppercase()} $reset")

    selectionMainCharacter()
    characterUser.name = ""
}

// die Auswahl für den Computer wird per Zufallsgenerator, je nachdem ob Team oder einzelner Charakter, getroffen
// es kommt kein Charakter doppelt in einem Team vor
fun characterComputer(){

    if (characterUser.name.isEmpty()){
        val listOfCharactersForRandom = mutableListOf<Character>()

        while (listOfCharactersForRandom.size < 3) {
            val randomCharacter = characterList.random()
            if (randomCharacter !in listOfCharactersForRandom) {
                listOfCharactersForRandom.add(randomCharacter)
            }
        }
        teamComputer.add(listOfCharactersForRandom[0])
        teamComputer.add(listOfCharactersForRandom[1])
        teamComputer.add(listOfCharactersForRandom[2])

        Thread.sleep(2000)
        println("\nDu trittst an gegen: $blue${teamComputer[0].name.uppercase()}, ${teamComputer[1].name.uppercase()}, ${teamComputer[2].name.uppercase()} $reset")

        selectionMainCharacterComputer()
        println("\nDer Hauptcharakter des Gegners ist $blue${mainCharacterComputer.name} $reset")

    } else if (characterUser.name.isNotEmpty()){
        do {
            val selection = characterList.random()
            characterComputer = selection
        } while (characterComputer == characterUser)

        Thread.sleep(2000)
        println("\nDu trittst an gegen: $blue${characterComputer.name.uppercase()} $reset")
    }
}

// diese Funktion zählt die Siege und gibt sie in einer Println aus
fun newRoundOrNotAndCountRoundsWon() {

    var check = false

    while (!check) {
        print("\nMöchtest du noch eine Runde spielen? 'Ja' oder 'nein': ")
        selectionUserString = readln().lowercase()

        try {
            if (selectionUserString == "ja") {
                println(
                    """
            
            Super, du möchtest noch eine Runde spielen.
            
            Auf gehts in eine neue Runde! 🤗
        """.trimIndent()
                )

            } else if (selectionUserString == "nein") {
                println(
                    """
            
            Ok, du möchtest keine Runde mehr spielen.
            
            Bis zum nächsten Mal! 👋
        """.trimIndent()
                )
            } else {
                println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
            }

            check = true

        } catch (ex: Exception) {
            println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
        }
    }
}

// diese Funktion nimmt die Eingaben vom Typ String und sucht sie in der Charakterliste und
// speichert den Charakter vom Typ Character, CharacterWithMoreStrength, CharacterWithBijuu, CharacterWithGenjutsu, CharacterWithGenjutsuAndSusanoo oder CharacterWithMedicalSkills in der Variablen characterUser
fun setCharacterForUser(string: String) {

    val inputList = selectionUserString.split(", ")

        for (character in characterList) {
            if (character is CharacterWithMedicalSkills) {
                if (character.name.lowercase() == string) {
                    characterUser = character as CharacterWithMedicalSkills
                }
            } else if (character is CharacterWithGenjutsu) {
                if (character.name.lowercase() == string) {
                    characterUser = character as CharacterWithGenjutsu
                }
                if (character is CharacterWithGenjutsuAndSusanoo) {
                    if (character.name.lowercase() == string) {
                        characterUser = character as CharacterWithGenjutsuAndSusanoo
                    }
                }
            } else if (character is CharacterWithBijuu) {
                if (character.name.lowercase() == string) {
                    characterUser = character as CharacterWithBijuu
                }
            }else if (character is CharacterWithMoreStrength) {
                if (character.name.lowercase() == string) {
                    characterUser = character as CharacterWithMoreStrength
                }
            } else {
                if (character.name.lowercase() == string) {
                    characterUser = character
                }
            }
        }

    if (inputList.size >= 3)
        characterUser.name = ""
}

// eine Liste zu einer Liste mit nur Kleinbuchstaben umwandeln
fun listToLowercaselist(list: List<String>): List<String>{

    var lowercaseList = mutableListOf<String>()
    for (characters in list){
        lowercaseList.add(characters.lowercase())
    }
    return lowercaseList
}

// fast jeder Charakter hat seine eigene kleine Grafik
fun grafik(selectionPlayer: String){

    Thread.sleep(1000)

    when (selectionPlayer) {
        "naruto" -> println(
            """
            $favoriteColorUser    
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⡀⠑⠒⠀⠠⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⣘⠖⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠴⡖⠒⠒⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠊⠁⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠈⠑⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠒⢄⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠔⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣄⣀⣀⣙⣦⡀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⣠⠚⢁⣀⡄⢰⠀⠀⢀⠀⠀⠀⣠⣤⣤⣤⣤⣶⣶⣶⣶⣶⣤⣤⣄⠀⠀⠀⠀⠀⠀⡀⠘⡄⠀⠀⠀⠁⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠚⠋⠉⠀⣘⣠⡇⠀⢠⣿⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⣠⡇⠀⢸⡟⠒⢼⡄⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⢀⣀⣀⣀⣀⣀⣴⣋⣸⠀⣴⣿⣿⠀⢠⠋⠉⠉⠉⠙⠉⠉⢉⡉⢀⡉⠁⠀⡃⢀⣴⣿⡇⢀⣿⡷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⣿⣿⣿⣿⠿⠛⠉⠙⠛⠿⢿⣧⢾⣿⣿⣿⢠⠧⠀⠀⡀⠀⠀⢠⡞⣩⣭⡋⠀⢀⢸⢠⠊⢼⣿⡇⣼⣿⡟⣲⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠿⠛⠉⠀⠀⠀⠀⠀⠀⢀⣼⡟⢾⠙⣿⣿⣾⣇⠄⠀⠀⠀⢰⣏⣣⣿⣿⡇⠀⠀⠟⠁⢀⣼⣿⣿⣿⠁⡿⢻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⠿⡇⢸⣆⡇⠈⠙⠻⣷⣶⣤⣤⣤⣀⣀⣀⣀⣤⣤⣤⣶⣶⣿⠟⠋⠀⢸⢰⣧⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⣠⡾⠟⠁⠀⢳⢷⣿⠃⠀⢰⡖⣿⣿⢯⣿⣟⠛⡟⠛⠻⡛⢛⣽⣿⣿⣿⡗⢦⠀⠘⣾⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⣀⣤⠞⠋⠀⠀⠀⠀⠈⣆⠛⡄⠀⠀⠣⡘⠿⢊⣘⠌⠙⠁⠀⠀⠹⠋⢸⡈⢻⣟⣡⠋⠀⠀⡏⡸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⣶⡿⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⣟⡶⢇⠀⠀⠀⠈⠉⠉⠉⠀⠚⠀⠀⠀⠀⠀⠀⠉⠉⠁⢀⠀⠀⢰⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡻⢁⠸⣀⣀⠶⠤⠤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠒⠒⠠⢤⡇⡇⠀⠀⠀⠀⠀⠀⢀⣀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠟⢹⢀⣷⡀⢀⡀⠶⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⠐⠢⠤⣀⡀⣼⣿⡃⠀⢀⣴⣶⣒⣉⣟⣿⠯⢖⡢⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣾⣿⣿⣿⡀⠀⣀⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⢤⡀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣮⣅
        ⠀⠀⣀⣠⣤⣴⣶⣶⣶⣶⣿⣿⣿⣿⣿⣿⣿⣷⣎⠁⠀⠀⠀⠤⠤⢴⠒⠒⠂⠄⠀⠀⢨⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
        ⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀⠀⢀⣤⣿⠙⠆⠀⠀⢀⣴⣵⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⡼⣱⠃⠀⣄⣠⣶⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣛⣿⠁⠇⠀⢀⣟⠻⠿⠿⠿⠿⠟⠛⢛⡛⠛⠛⡛⠛⠛⠛⠿⠿⠟⠛⠋⠉⠉
        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⡀⠀⡎⡸⠀⠀⢸⠀⠀⠀⠀⢠⣀⠖⠖⠉⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢠⠃⠀⠀⣿⣶⣶⣶⣶⠖⠁⠀⠀⡄⠐⠃⠀⠀⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢁⠎⠀⠀⣸⠿⠟⠛⠛⢣⠀⢀⣴⡟⠁⠀⢀⣀⡠⢤⣀⣀⠀⠀⠀⠀⠀⠀⠀
        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣏⠎⠀⡀⣠⡿⠒⠢⡀⣠⠎⠀⢸⡏⠀⢀⠴⣯⣅⢀⢀⡉⣀⠀⣀⣨⣿⣿⣿⣿
        $reset
    """.trimIndent()
        )

        "sasuke" -> println(
            """
            $favoriteColorUser    
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⢀⣨⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⣠⣴⡿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠀⠀⢻⣿⣿⣿⡿⣿⣿⣿⣿⠏⠀⣿⣿⠻⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠈⠁⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⢸⣿⣿⣿⠁⣿⣿⣿⠃⠀⢸⣿⠇⠀⢹⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⣀⠀⠀⠀⠀⣿⣿⠇⠀⣿⡿⠁⠀⠀⣼⠏⢀⣠⠴⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⠃⣿⠋⡀⢀⡉⢓⠦⢤⣼⣿⡴⠀⡟⠁⣀⣀⣴⠟⠛⣩⣤⡄⣸⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⣿⣿⣿⣿⡏⢰⠣⡞⢉⣿⣽⢿⠲⡐⢌⡇⠀⠀⠀⠀⡸⠁⣨⠖⣟⣩⣿⠉⢻⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀
        ⠀⠀⠀⠀⢀⣼⣿⡿⢛⣿⣿⣿⣿⣿⠇⠀⠀⠙⢦⣻⣿⣛⣤⣼⡀⠀⠀⠀⠀⢰⠁⠐⣧⣀⣻⣿⣋⡠⠋⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀
        ⠀⠀⠀⢀⣾⡿⠋⠀⢸⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⡀⠁⠉⠀⡀⢀⣿⣿⣿⣿⣿⠁⠀⠀⠀⠀
        ⠀⠀⢀⡾⠋⠀⠀⠀⣸⣿⣿⣿⠳⣼⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠀⠀⢘⠷⡦⢀⡶⠇⢸⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀
        ⠀⠀⠈⠀⠀⠀⠀⢠⣿⣿⣿⡏⠒⠚⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡤⠛⠉⠁⢀⣿⣿⣿⣿⣿⠙⢿⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠋⢸⣿⣿⣿⡄⠀⢸⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠁⠀⠀⡈⠉⠛⠿⠿⣇⠀⠈⠃⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡟⢸⣿⣿⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠒⠓⢠⡇⠀⠀⠀⠀⠀⠉⠒⠤⡀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠁⢸⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⡀⠀⠀⠀⣒⡖⠊⡇⠀⠀⠀⠀⠀⠀⠀⠀⢨⠆⠀
        ⠀⠀⣆⡀⠀⠀⠀⠀⢸⡏⠀⣾⣿⠟⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠉⠀⠀⠈⠁⠈⠉⠀⣠⠾⠁⢨⠇⠀⠀⠀⢀⠀⠀⠐⠀⡸⠀⠀
        ⠀⠀⢸⡉⠉⠉⠉⠉⠉⡇⢀⡿⠁⠀⣿⣿⣿⣿⠷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⢁⣼⠀⠀⠀⠀⠀⠀⠀⠀⡴⠁⠀⠀
        ⢆⠀⠀⢇⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⣿⠃⢹⣿⣤⠈⠓⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠔⢹⡏⠀⠀⠀⢠⡞⠀⠀⡜⠀⠀⠀⠀
        ⠈⢆⠀⠘⡄⠀⠠⡶⠿⢠⠂⠀⠀⢸⠃⠀⢸⣿⠈⠳⣄⠀⠈⠲⠤⣀⣀⣀⠀⣀⣀⠤⠚⠁⢀⣿⡇⠀⠀⢰⣋⠄⠀⡰⠁⠀⠀⠀⠀
        ⠀⠈⢆⠀⢱⠀⠀⠐⠊⠁⠀⢀⣠⠧⠤⢤⣼⠏⠀⠀⠈⠳⢤⡀⠀⠀⠈⠉⠋⠁⠀⠀⠀⢠⡾⢛⠃⠀⠠⡾⠃⠀⡰⠁⠀⠀⠀⠀⠀
        ⠀⠀⠀⠣⡀⢣⠀⠀⠀⠀⠀⡋⠁⠀⡴⠚⠁⠀⠀⠀⠀⠈⡄⠑⠢⢄⣀⣀⣀⣀⠤⠒⠋⠁⠀⢸⠀⠂⣒⡇⠀⡼⠑⡄⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠑⠄⢂⠀⠀⢀⠞⢁⠴⠋⠀⠀⠀⠀⠀⠀⠀⠀⠘⡄⠀⠀⠀⠀⠀⠀⠀⢀⠎⠀⠀⠘⠀⠈⠿⠀⡜⠁⠀⢸⡀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠈⣮⢦⣠⣷⠚⠁⠀⠀⠀⠀⢀⠀⠂⠀⠀⠀⠀⠈⢦⡀⠀⠀⠀⠀⠠⠀⠀⣀⠀⡇⠀⠀⢀⡜⠀⠀⠀⡸⠁⠈⠐⠂⢀
        $reset
    """.trimIndent()
        )

        "sakura" -> println(
            """
            $favoriteColorUser    
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠃⢺⠱⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣇⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡜⠀⡇⢸⠀⠘⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞⠀⠀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞⠁⠀⡇⢸⠀⠀⠈⢧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣇⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠁⠀⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡸⠁⠀⠀⣾⠾⡄⠀⠀⠀⠻⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡜⠀⢠⡇⠀⢠⠀⠀⠀⠀⠀⠀⠀⣰⠃⠀⠀⠀⠘⣄⡇⠀⠀⣠⣄⠈⠳⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⠀⠀⠀⠀⠸⡄⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡇⠀⡼⠀⠀⢸⠀⠀⠀⠀⠀⠀⢠⠇⠀⠀⠀⠀⠀⢻⣳⠀⠘⢿⣿⡷⠀⠘⠦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⠀⢠⠇⠀⠀⠈⠀⠀⠀⠀⠀⠀⡿⠀⠀⠀⠸⠄⠀⠀⢿⡄⠀⠀⠉⠀⠀⠀⠀⠈⠲⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠂⠀⠀⠀⠀⣧⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⠀⣼⠀⠀⠀⡀⠀⠀⠀⠀⠀⢸⣅⠀⠀⠀⠀⠀⠀⠀⠘⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⢺⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠇⢠⠃⠀⠀⢠⠇⠀⠀⠀⠀⠀⣾⣬⣝⣦⡀⠀⠀⠸⣄⠀⠘⣇⠀⠀⡀⠀⢀⡞⠀⢀⣠⣴⣻⣟⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠸⡄⢸⣆⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣠⡟⠀⠀⠀⢸⡆⠀⠀⠀⠀⠀⣿⠙⡏⠩⣿⠳⣦⣄⣀⡀⠀⣼⣆⠘⣇⣀⣈⣤⣶⡿⠛⠛⣠⡏⣿⠀⠀⠀⠀⠀⢰⠀⠀⠀⢰⣷⠘⣿⡀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⡏⠀⠀⢀⠀⡀⠇⠀⠀⠀⠀⢸⠘⣦⣙⠶⠴⠞⠉⢻⣛⣯⡟⠁⠈⠀⢈⡿⢫⡾⣁⣙⣒⣒⣉⡴⢿⡇⠀⠀⠀⠀⡌⠀⠀⠀⠀⡏⡆⡇⣇⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⠀⠀⡴⡇⠀⡇⢰⠀⠀⠀⠀⡿⠀⠀⠈⠉⠉⠉⣩⠭⠗⠛⠿⠄⠀⠀⡾⠞⠉⠙⠲⠄⠀⠀⠀⠀⠈⡇⠀⠀⠀⢀⡇⠘⡇⠀⠀⡇⢳⡇⢸⡄⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠇⣠⠞⣿⢧⠀⡇⣸⠀⠀⠀⢈⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⡆⠀⠐⣿⠃⠀⢸⠀⠀⣷⠘⠇⠈⣇⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣧⠞⠁⢰⣻⡏⠀⣷⢿⠀⠀⠀⠀⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡇⡇⠀⢸⣸⠀⠀⢪⡇⠀⢻⠀⢠⡀⠸⡄
        ⠀⠀⠀⠀⠀⠀⠀⠀⢠⡟⠁⠀⠀⢸⣿⡇⠀⡟⠸⡄⠀⠀⠀⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⡇⠃⠀⣿⣿⡧⠤⠜⠛⠲⠾⠦⣤⣧⡀⢳
        ⠀⠀⠀⠀⢀⡠⠒⠊⠉⠉⠉⠉⠉⠙⠛⠦⣤⣧⣀⡇⠀⡀⠀⣿⣿⣦⠀⠀⠀⠀⠀⠀⠀⠲⣦⣄⠀⣠⣶⠂⠀⠀⠀⠀⠀⢀⡾⣿⣿⡇⠀⢀⣧⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉
        ⠀⠀⡀⠔⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⡇⠀⠸⠀⣿⣿⣿⣷⡄⠀⠀⠀⣀⠀⢀⣈⠽⠛⠛⠧⣄⣠⠖⠃⠀⣠⠏⣸⣿⣿⣇⠀⢸⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠴⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⡄⠀⡇⣿⣿⣿⣿⣟⢦⡀⠀⣠⠟⢋⣀⣀⣀⣀⣀⣈⣻⠇⣠⡾⢃⣼⣿⣿⣿⡿⠀⣷⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣿⣿⣿⣿⣇⠀⢧⢿⣿⣿⣿⣿⣧⣝⡟⢁⡴⠋⠀⠀⠀⠀⠀⠈⠙⣿⡫⣶⣿⣿⣿⣿⣿⡇⣼⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⣿⣿⣿⡀⢸⣸⣿⣿⣿⣿⡟⠉⡴⠋⠀⠀⢀⣤⠤⢔⣶⠦⢤⣤⣴⣿⣿⣿⣿⣿⣿⢣⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣷⣸⣯⣿⣿⣿⣿⡵⠊⠀⠀⠀⣴⡿⠊⠉⠉⠙⠒⠛⢻⡛⢻⣿⣿⣿⣿⣿⣾⣿⣿⣿⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠘⢷⣖⢤⡀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢀⣀⣀⠴⠋⠁⠀⠀⢀⡠⠤⠤⠤⠤⠶⠾⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⢿⣧⣀⣀⣀⣀⣀⠤⠤⠶
        ⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣗⣲⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⢀⣠⠾⠭⠤⠤⣄⣀⣈⣁⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠐⠒⠉⠁⠀⠀⠀⣀⡀⢀⣀⣛⠿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠱⡀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⢀⣤⣴⣾⠽⠛⠋⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⣀⣳⡀⠀⠀⠀⠀⣀⣠⣆⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⢠⡿⠛⠷⣄⣀⣠⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠈⠉⠉⠉⠉⠉⠉⠐⠓⠛⠛⠛⠛⠛⠿⠿⠟⠛⠛⠛⠛⠛⠋⠉⠙⡆⠀⠀⠀⠀⠀⠀⠉⣷⢶⣯⡟⠀⣯⠉⠒⠦⣄⣀⣀⠀⠀⠀⢀⣀⣠⣶⣶⣶⣶⣤⣤⣤⣤⣄⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡄⠀⠀⠀⠀⠀⢀⣗⡉⠀⢳⣤⣟⣇⣀⣀⣠⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠀⠀⠀⠀⢀⣼⣿⣯⢳⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢐⡭⣺⣿⣟⣺⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣾⣿⣿⣿⣯⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⣀⣠⣤⣤⣤⣴⣶⣿⣿⣿⣿⣿⣿⣿⣿⣷⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀
        ⠀⠀⢀⠀⠀⠀⠀⠀⠠⣶⣶⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀
        ⠀$reset
    """.trimIndent()
        )

        "shikamaru" -> println(
            """
            $favoriteColorUser    
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⡀⠀⠀⠀⠀⢠⡦⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡄⠀⣾⣿⣿⣿⣿⣇⠀⠀⢀⣴⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣾⣿⣿⣿⣿⣿⣿⣤⣴⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣦⡀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⡤⠀⢠⡄⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣶⣤⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣤⣶⡟⠁⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⣤⣤⣤⣿⣿⣿⣿⣿⣿⣿⠿⠿⠿⠿⠿⢿⣿⣿⣿⣿⣁⣤⡾⠃⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿⠿⠋⠁⣀⣀⣰⣦⣤⣤⣤⣟⣛⡻⢿⣿⣿⠟⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣿⣿⣥⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣧⣀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⣿⡟⢿⡈⠻⠌⠻⠀⠹⠀⠙⠙⢿⣿⣿⣿⡿⣿⡿⠟⣿⣿⣿⣿⣿⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣿⣿⣿⣿⡇⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⡿⠋⠈⠉⠀⠐⠋⢡⠟⣿⣿⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣸⣿⣿⣿⣿⠈⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⢿⣿⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡧⡘⢟⣿⡿⠝⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⡿⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠷⢿⠀⢻⡇⠀⠀⠀⠓⠤⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣽⠃⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡇⣠⣿⠀⠈⠁⠀⠀⠀⡀⠀⠀⠉⠳⣄⡀⠀⠀⣠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡟⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⣣⣽⡆⠀⠀⠀⠀⠀⢿⣽⠏⠑⠲⣌⡙⠶⠾⠁⠀⢿⣤⡤⣶⣶⠶⠖⠚⠁⣸⠁⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢧⢈⡉⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠉⠀⠀⠀⠀⡜⠻⣟⣟⣉⣩⡿⠁⢰⠃⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⠤⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡞⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⠀⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡼⠃⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣨⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣄⡀⢀⠀⠀⠀⠀⠀⠀⠀⣰⠃⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⣶⣶⣟⡯⠿⠟⢻⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠛⠛⠁⠀⠀⠀⠀⢀⡼⠉⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠁⢿⣏⠁⠰⣀⠠⠒⢻⠀⢳⡄⠀⠀⠀⠓⠒⠒⠒⠒⠒⠒⠒⠒⠁⠀⣠⣞⡲⢄⡀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠃⠀⠀⠈⢻⡇⠀⢹⡄⠀⡀⡇⠀⠻⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣎⣡⠈⢻⣷⠙⣆⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⠀⠀⠀⠀⢸⡇⠀⠀⣿⣄⣦⠇⠀⠀⠙⢾⡽⠲⢤⣄⣀⣀⣀⣠⢴⣿⡭⠟⠁⣠⣾⠏⠀⣽⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⡼⡇⠀⢸⠓⣻⡿⠀⠀⠀⠀⠀⠙⢶⡬⢚⣓⠋⠙⠀⣸⡿⠁⠀⢸⣽⠁⠀⣸⣿⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⢀⡇⠀⠀⠀⠀⣷⠀⠀⢸⠟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠙⠒⠿⠶⠶⢺⣿⡇⠀⠀⣎⡇⠀⢠⡗⡟⠀⠀⠀⠀
        ⠀⠀⠀⠀⢀⡠⠔⠛⠁⡇⠀⠀⠀⠀⡿⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠄⠀⠀⢹⠁⠀⢠⢿⠀⠠⠎⣴⡷⣄⠀⠀⠀
        ⣀⠀⠠⠒⠁⠀⠀⠀⠀⢸⠀⠀⠀⠀⠇⠂⠀⠸⡇⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⢠⠎⠀⠀⠀⡼⠀⠀⡿⡟⠀⢐⢦⡟⠀⠀⠙⢦⣄
        $reset
    """.trimIndent()
        )

        "kakashi" -> println(
            """
            $favoriteColorUser
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠀⠀⢤⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠲⡄⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡼⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡁⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣇⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡤⢖⣠⡴⠒⠊⠁⠠⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠘⣆⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⢞⣡⠖⠉⠚⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠒⠲⠶⠶⠶⠶⢒⡲⠒⠀⠀⠀⠀⠈⢣⣀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⠏⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠴⠭⠤⠤⣄⣀⣀⡀⠀⠀⠈⠳⠤⣀⡀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠴⠛⠁⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣰⠃⠀⠀⠀⣀⣤⣤⣤⣤⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡔⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣿⠀⠀⣠⣾⡿⡝⠂⠀⠈⠉⠙⠻⣶⣤⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢉⣩⠥⠶⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠃⢠⣾⣿⣿⡇⠀⠀⠀⠰⣞⢽⣿⠀⠉⢷⡀⢰⠀⠀⠀⠀⠀⠰⡚⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⠿⠿⣿⢿⣶⣤⣀⠉⠉⠀⠀⠀⢳⣸⠀⢸⠀⠀⢀⠀⠈⠳⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠏⠘⣉⣛⣉⣳⠻⡝⠿⣿⣦⣄⡀⠀⢸⣿⡄⣼⠀⠀⢹⡅⠀⠈⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠇⠀⠀⠛⠛⠛⠋⣠⣿⣷⣮⡻⣿⣿⣶⣾⣿⣧⣿⠸⢦⡀⢳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⡻⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢤⡆⠙⢾⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡆⣽⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⠃⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠿⢧⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣤⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡏⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⠁⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡤⠖⣾⡷⢤⠋⢬⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⡆⢠⠁⠀⣞⠙⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠁⠀⡟⡇⠈⠓⢶⣿⣿⣿⣾⣿⣿⣿⣿⣷⣿⣿⠋⢉⢹⠀⠀⣿⠀⢳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⡇⠀⢠⠃⢈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠁⢸⠀⠀⣿⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⡿⠀⠐⡀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⠀⣼⠀⢰⠻⠀⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⢀⣠⠤⠖⢻⠀⠀⣷⡆⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⣼⡇⢠⠏⠓⠦⣄⠀⠀⠀⠀⣠⠖⠋⠁⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⣠⠶⠋⠉⠀⠀⠀⠈⠳⣄⣿⣳⠶⠋⠁⠐⠚⠛⠻⠈⡿⣏⠉⠉⠉⠉⠀⠀⠻⣶⣿⠷⠛⠀⠀⠀⠀⠉⠑⠲⣌⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⢠⠞⠳⢄⡀⠀⢀⣀⠀⠀⠀⠀⠈⣏⣶⢦⠀⠀⠀⠀⠀⠀⣿⠚⠀⠀⠀⠀⣀⠀⣴⣳⠋⠀⣀⣠⠖⠀⠀⠀⠀⣀⠬⠷⡄⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⢸⣠⣿⣦⡙⢦⡀⠈⠳⣄⣀⡀⠀⢸⠸⡄⠳⡀⠀⠀⠀⢸⡙⠀⠀⠀⠀⡸⠁⢰⠣⣇⠀⠀⠛⠁⠀⠀⢀⣴⣺⣵⣿⣆⡇⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠈⡿⣿⣿⣿⣦⡙⢦⡀⠈⠙⣃⣠⠼⣠⠇⠀⢣⠀⠀⠀⣿⡇⠀⠀⠀⡼⠁⠀⠘⠲⢭⣓⣒⠦⢤⣠⠔⣫⣾⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⢠⡇⢸⣿⣿⣿⣷⣤⣉⣉⣭⠽⠖⠋⠁⠀⠀⠘⡇⠀⠀⡿⡇⠀⠀⣼⠁⠀⠀⠀⠀⠀⠀⠉⢹⣶⣶⣾⣿⣿⣿⣿⡟⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠈⡆⣿⣿⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⠀⠀⢱⠀⠀⡇⡇⠀⢰⠃⠀⢀⢀⣀⣀⣀⣀⢸⣼⣿⣿⣿⣿⣿⣿⣿⣷⣧⡀⠽⣃⣀⠀⠀⠀⠀⠀
            ⢀⣾⣿⣿⣿⣿⣿⠟⠻⠧⣉⠛⢅⠈⠙⠋⠉⠙⠛⠛⡆⠀⠁⠁⠀⡾⠋⢹⡿⠁⠀⣿⠀⠀⡉⣿⣿⣿⣿⣿⣿⣿⣿⡏⡏⡇⠀⠀⠀⠀⠀⠀⠀⠀
            ⢸⣿⣿⣿⣿⣿⣿⡙⠓⠂⢌⢦⠘⣄⣀⡀⠀⠀⠀⠀⡇⠀⣸⠁⠀⡇⠀⢸⡇⠀⢸⣿⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⠿⠁⠀⠀⠀⠀⠀⠀⠀⠀
        $reset    
        """.trimIndent())

        "jiraiya" -> println(
            """
            $favoriteColorUser
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⢀⣾⣯⢳⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠚⠁⠀⠀⠀⠀⢀⡾⠿⣿⠋⣤⡀⠉⠑⠢⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠔⠉⠀⠀⠀⠀⠀⠀⢠⡟⠁⢸⠉⢠⣼⡏⢠⣄⣀⣿⡙⢦⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠚⠁⠀⠀⠀⠀⠀⠀⠀⢠⣿⠀⢀⠛⠀⣸⣋⠀⣼⣿⣿⣿⣦⡄⣿⡿⠁⠀⠀⢀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠐⠀⠀⠀⠀⠀⡠⠊⢠⡞⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⣿⣷⣝⠉⠀⣿⣯⣿⣿⡿⠀⠀⡇⠀⠀⠀⠈⢆⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠚⠀⡠⠋⠀⠀⠀⠀⠀⠀⠀⠀⣸⢷⣀⣀⡉⢻⣿⣿⣿⣆⣠⡈⠙⠿⠃⠀⢠⠃⣄⠀⠀⠀⠈⢣⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠔⢡⡞⠁⠀⠀⠀⠀⠀⠀⠀⠀⢠⠟⢀⡿⠿⠟⠓⠻⣿⡿⠟⢿⣿⣿⣿⣾⣦⡎⠀⢹⡟⠦⣀⠀⠈⠂⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⡠⠋⣴⠏⠀⠀⡖⠀⠀⠀⠀⠀⠀⢀⣿⠞⠉⠀⠀⠀⠀⠤⠟⠃⠀⡴⣿⣟⢦⣹⠏⠀⠀⠀⢧⠀⠈⠁⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠎⢀⠞⠁⠀⠀⣰⠁⠀⠀⠀⠀⠀⠀⣼⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡏⠂⠸⣿⠇⠀⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⣰⠋⢀⣴⠆⢠⠃⠀⠀⠀⠀⠀⠀⢰⠃⡾⠓⠒⠂⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⢻⠄⠀⠀⠀⠀⠀⢣⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⢁⠔⠁⡌⢀⠇⠀⣠⠀⠀⠀⠀⢀⣿⠸⢠⡞⠦⣀⠀⠀⠀⠀⣶⣄⣠⠇⠀⠀⢸⡇⠀⠀⠀⡷⡀⢸⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠐⠁⠀⡼⠀⠎⢀⡔⠙⠀⠀⡀⠀⣼⡟⠀⠸⡈⠳⠀⠉⠓⠒⠤⢌⣉⣀⠀⢰⣤⠇⠃⠀⡀⠀⡇⠱⡀⡇⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⣸⠁⠐⡠⠋⠀⡆⠀⣰⠇⢠⣿⠇⠀⠀⠈⠒⠠⠤⠄⣀⣀⣀⡀⣾⠁⡾⡟⠀⠀⢠⡇⠀⠀⠀⢳⣇⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⢠⠇⠀⠀⠀⠀⢀⠃⣰⠃⡇⢸⣿⡀⠀⠀⠀⠘⠶⣦⡤⠤⠤⢤⡬⠀⢀⡟⠀⠀⠀⡈⢰⠀⢰⠀⠀⠹⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⡼⠀⠀⠀⠀⠀⢸⢠⠃⢸⠀⢸⠛⢿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠋⠀⠀⠀⠀⠀⠸⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⢰⠃⠀⠀⠀⠀⠀⣇⡏⠀⢸⠀⢸⠀⠀⠈⠛⠶⢤⣀⡀⠀⠀⠀⣀⡜⠁⠀⠀⠀⠀⠀⠀⣷⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⣀⣤⣶⡏⠀⢀⡶⠀⠀⠀⠀⠀⠀⢸⡀⢸⢱⠀⠀⠀⠀⠀⠀⠉⠉⠉⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⣿⡄⢸⠀⠀⠀⠀⠀⠀⠀⠀
            ⣠⡶⣿⣿⣿⣿⠁⠀⣾⡇⠀⠀⠀⠀⠀⠀⠀⣇⡇⠀⢣⠀⠀⠀⠀⠀⠀⡰⢻⠃⠀⠀⠀⠀⠀⠀⠀⠀⢰⠽⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠁⢠⣿⣿⣿⡟⠀⣼⣿⠀⠀⠀⠀⠀⠀⠀⢰⠿⣵⠀⠈⢧⠀⠀⠀⢠⠞⠁⡜⠀⠀⠀⢀⣀⣀⠤⠤⠦⢾⣠⣧⡇⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⣸⣿⣿⣿⡇⣰⣿⣿⠀⠀⠀⠀⠀⠀⠀⢸⣧⣼⣀⡀⠈⢧⣠⠴⠃⠀⠀⡇⠀⡠⠊⣡⡴⣖⠋⠉⠓⢦⣀⡉⢹⡦⣄⠀⠀⠀⠀⠀⠀
            ⠀⣿⣿⣿⣿⢠⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣶⣶⣤⣤⡾⢊⣴⡾⠋⢃⢸⣤⣤⣄⡀⠀⠉⠉⠑⠒⢧⠀⠀⠀⠀⠀
            ⢰⣿⣿⣿⣿⣾⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⢸⣿⣿⠻⢿⣿⠿⢿⠾⢿⣿⣯⣴⠟⠁⠀⣀⣾⢸⠀⠀⡍⠛⠷⣆⡀⠀⠀⠈⣷⣤⣄⣠⡀
            ⢸⣿⡿⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⠀⣾⡓⣿⣶⠋⠙⢦⡠⢿⣩⢿⡽⠁⠀⠀⠐⠷⢺⠘⡀⠀⠘⣆⠀⠀⠉⠓⠥⣄⣿⠀⠈⠉⠛
            ⣾⡟⠀⢿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀⠀⣿⣿⠋⠙⣦⡴⠛⣧⠜⣱⠋⠀⠀⠀⠀⠀⠀⠘⡆⢇⠾⠟⠛⠿⠒⢤⣀⡀⢈⣷⠀⠀⠀⠀
        $reset    
        """.trimIndent())

        "gaara" -> println(
            """
            $favoriteColorUser
               ⠀⠀⠀⣿⣿⠀⣿⣿⣿⠿⣿⣏⠙⠻⠿⣿⣿⣿⡏⠙⢿⣿⡿⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
               ⠀⠀⠀⣿⣿⣿⡛⠛⠛⠿⠿⠿⠀⠀⠀⠀⠉⠉⠁⠀⠀⠙⠇⠀⢿⣿⡿⠿⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿
               ⠀⠀⢸⣿⡧⢼⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⡇⢠⣿⠋⣿⣿⣿⣿⣿⣿⡿⢻⣿⣿⣿⣿
               ⠀⠀⢸⣿⡿⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠛⠿⣯⣄⣼⡿⠛⠛⢿⣿⣿⣿⣿⠄⣸⣿⣿⣿⣿
               ⣶⡒⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⣶⣾⠋⢹⡟⠀⠀⠀⠀⢹⣿⣿⣿⣠⣿⣿⣿⣿⣿
               ⠀⠀⢸⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⣆⣼⡇⠀⠀⠀⠀⠘⣿⣿⣟⣿⣿⣿⣿⣿⠋
               ⢈⡴⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣙⣿⡇⠀⠀⠀⠀⢸⣿⡟⢻⣿⣿⣿⡿⠇⠀
               ⠫⢶⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⡇⢀⣀⣀⠀⣼⡿⠟⠋⠉⠀⢀⡠⠄⠀
               ⣠⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣿⡇⠀⠀⠁⠀⡏⠀⠀⠀⢀⠔⠋⠀⠀⠀
               ⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⣤⠞⢻⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⣿⡇⠀⠀⠀⠀⡇⠀⠀⣴⠃⠀⠀⠀⠀⠀
               ⡿⣀⠀⠀⠀⠀⠀⠀⢀⡀⢀⣀⣀⣴⣿⣼⣋⣠⢔⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⢿⠀⠀⠀⠀⢠⠀⣠⠎⠹⠀⠀⠀⠀⠀⠀
               ⡷⣿⣰⣿⠀⣀⣟⣠⣾⡷⣠⣿⡿⣻⡿⢋⣿⣿⣿⣾⣧⠀⠀⠀⠀⠀⣠⠀⠀⠀⢠⣀⣸⣿⣿⠈⠉⠐⠂⢸⡶⠁⠀⠐⡄⠀⠀⡀⠀⠀
               ⢀⣷⣿⡇⢰⣿⣿⠏⣿⣿⢻⡟⢀⡿⠁⠀⢩⣾⣏⠀⢻⣦⡀⠀⠀⠀⣿⠀⠀⣇⢸⣿⣿⡇⡏⠀⠀⠀⠀⠘⠁⠀⠀⠀⠁⣠⠎⠀⠀⠀
               ⢸⡿⣿⣷⣿⡟⣿⣦⣿⠃⡾⠆⠘⠁⠀⠐⠊⠉⠉⠁⠘⣿⣧⣀⠀⠀⣿⠀⣀⣿⢸⣿⣿⣿⡇⠀⠀⠀⠀⢰⠀⠀⠀⣠⠞⠁⠀⠀⠀⠀
               ⠋⠀⣿⣿⡈⠳⢯⣿⣿⡆⠘⢦⣠⣤⣲⡿⢶⠶⠶⢦⡄⢻⣻⣿⡆⣼⣿⣿⡿⣿⣿⣿⡇⡟⠀⣀⣀⣀⡀⠈⠦⣠⠜⠁⠀⠀⠀⠀⠀⠀
               ⠀⢰⣿⣿⡇⠀⠀⠀⠉⠀⠀⠀⠉⠛⠿⠦⠤⠽⠿⠋⠀⠀⠈⣿⣿⣿⢿⠛⢳⣿⣿⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
               ⠀⠘⡏⠹⣇⠀⠀⠀⡀⠀⠀⠀⠀⠀⣠⠤⠒⠒⠒⠒⠦⣄⣀⠸⢸⡿⢈⢼⣿⣿⣿⡿⠁⠀⢄⠀⠀⠀⠀⠀⠀⠀⠘⠢⣀⠀⠀⠀⠀⠀
               ⠀⠀⠇⠀⠹⡄⠀⢸⠀⠀⠀⠀⠀⢸⡁⠀⠀⠀⠀⠀⠀⡼⠀⠈⠉⠛⠐⠟⠛⠛⠉⠀⠀⠀⠀⠙⠢⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
               ⠀⠀⠀⠀⠀⢳⡀⠀⠱⡔⠀⠀⠀⠀⠑⠠⠤⢀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠢⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
               ⠀⠀⠀⠀⠀⠘⠷⡄⠀⠻⢶⣤⣀⠀⠀⠀⠀⠀⠀⠈⢙⠦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠱⡀⠀⠀⠀⠀⠑⢦⠀⠀⠀⠀⠀⠀⠀⠀
               ⠀⠀⠀⠀⠀⠀⠀⠙⣦⠀⠓⠠⠄⠁⠀⠀⠀⢀⡠⠖⠁⢀⡼⠳⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠱⡀⠀⠀⠀⠀⠀⠳⡀⠀⠀⠀⠀⠀⠀
               ⠀⠈⠗⠀⠀⠀⣠⡾⠛⢳⣄⣀⣀⣀⡤⠴⠚⣉⣀⣤⡶⠋⠀⠀⣸⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢱⡀⠀⠀⠀⠀⠀⠰⡄⠀⠀⠀⠀⠀
               ⠀⠀⠄⠀⠀⣸⠟⠁⠀⠀⠛⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⠀⠀⣰⣿⢻⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠘⠄⠀⠀⠀⠀
               ⠀⠀⠀⠀⣴⡏⠀⠀⠀⠀⢰⣿⣿⣿⣿⣿⣿⡿⠋⠀⠀⠀⢰⣿⡇⠐⢹⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
               ⠀⠀⠀⢰⡟⣽⣧⠀⠀⠀⣼⣿⣿⣿⣿⣿⡿⠁⠀⠀⠀⠀⣿⣿⡇⠀⠈⢿⣟⠢⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀
               ⠀⠀⠄⣼⠃⣿⠇⠀⠀⢰⣿⣿⣿⣿⣿⠟⠀⠀⠀⠀⠀⢰⡿⣽⡇⠀⠀⠉⠛⣦⡀⠈⠑⠂⠤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
               ⠀⠀⢀⡟⢠⡿⢀⠀⢀⣿⣿⣿⣿⣿⠋⠀⠀⠀⠀⠀⠀⢻⡏⢹⣧⠀⠀⠀⠐⠈⠙⣦⣀⠀⠀⠀⠙⠦⣀⠀⠀⠀⠀⠀⢀⡠⠀⠀⠀⠀
               ⠀⠀⢸⠃⢸⡇⠀⠘⣿⠿⠿⣿⡿⠁⠀⠀⠀⠀⠀⠀⠀⣿⡇⠻⣿⠀⠀⠀⠀⠀⠀⠀⠈⠛⠶⢤⣄⣀⣀⣉⣒⣶⣶⣾⠋⠀⠀⠀⠀⠀
               ⠀⠀⣼⠀⣿⠁⠀⣰⣟⣠⣴⡾⠁⠀⠀⠀⠀⠀⠀⠀⢰⣿⣧⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣈⣉⣉⣉⣀⣽⡟⠁⠀⠀⠀⠀⠀⠀
            $reset   
            """.trimIndent())

        "orochimaru" -> println(
            """
            $favoriteColorUser
            ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿
            ⣿⣿⣟⣿⣽⣿⣟⣯⣿⣿⣽⣿⠋⠀⣀⢀⣀⣀⣠⣤⣴⣶⠟⠀⠀⠀⠀⠀⠀⣼⣿⣿⣿⣻⣿⠿⣷⣶⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⣿⣿
            ⣿⣿⡿⣿⣻⣯⣿⣿⣿⣽⣿⠿⠿⠿⠻⠿⠻⠟⢛⠛⠋⠀⠀⠀⠀⠀⠀⠀⡒⣿⣿⣿⣽⣿⡇⠀⡄⡈⢉⠛⠛⠿⠿⠶⠶⣤⢤⣤⣿⣿
            ⣿⣿⣿⣿⢿⣿⣻⣷⣿⡿⠃⠀⠀⠀⠀⠀⠑⢊⠤⠈⠄⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿⣻⡿⠀⡰⠐⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿
            ⣿⣿⣷⣿⣿⢿⣿⣿⡿⢓⡞⣛⡛⣟⡞⡶⣶⣦⣌⠁⣊⠀⠀⠀⠀⠀⠄⠀⣿⣿⣿⣟⣿⠃⠀⡔⠁⣀⣤⣤⣤⣤⣴⠶⡶⢶⢤⠤⣼⣿
            ⣿⣿⣿⣾⣿⣿⣯⣿⣿⡿⠾⢷⣿⣶⣾⣵⡒⢦⠻⣦⠀⠁⠀⠀⠀⠀⠀⢸⣿⣿⡿⣿⡏⠀⠐⠀⣼⣏⡳⣞⣴⣧⣾⣵⣿⣾⣮⣷⣼⣿
            ⣿⣿⣷⣿⢿⣾⡿⠐⡌⢷⣄⠀⠀⠂⠩⢿⣿⢣⢛⡼⣧⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⡟⠀⠀⠀⢸⡟⣼⣿⢿⣿⢉⠌⠦⣿⣆⠀⠈⢹⣿
            ⣿⣿⣟⣿⣿⡿⢁⠳⡈⢆⢻⡄⠀⠀⠀⠒⢻⣧⢫⡔⣻⡆⠀⠀⠀⠀⣼⣿⣿⣟⣿⠁⠀⠀⢠⣿⢣⣿⠧⣉⣿⡌⠜⢢⠑⣻⡄⠀⠘⣿
            ⣿⣿⣻⣯⣿⡇⡘⠤⡑⡎⢩⣟⠀⠀⠀⠀⠈⢿⣧⢚⡥⢿⡀⠀⠀⢠⣿⣿⣿⣿⠃⠀⠀⠀⢼⣯⣿⠏⡰⢥⣿⠇⣊⠥⠚⢼⣧⠀⢈⣿
            ⣿⣿⣟⣿⣿⠇⡘⢆⠱⣈⣾⠋⠀⠀⠀⠀⠀⠘⣿⡜⢲⡹⡇⠀⠀⣼⣿⣿⣿⠏⠀⠀⠀⢀⣿⣻⣿⢠⠑⣺⣿⡘⠄⣎⠡⣶⡿⠀⢠⣿
            ⣿⣿⢿⡇⢿⡇⠱⣌⣲⡽⠃⠀⠀⠀⠀⠀⠀⠀⢸⣏⢧⢓⣿⠀⢰⣿⣿⣿⡟⠀⠀⠀⠀⢸⣿⡟⠙⣧⡘⢼⣟⠠⡍⢤⣳⠟⠀⠀⢰⣿
            ⣿⣿⡿⠙⠳⠟⠟⠋⠁⠀⠀⠀⠀⠀⠀⢀⣀⣀⡀⣿⢎⡞⡼⡇⣾⣿⣿⡟⠀⠀⠀⠀⠀⣺⣿⣇⠀⠘⠷⣾⣋⣴⣼⠞⠁⠀⠀⠀⣼⣿
            ⣿⣿⠓⠚⠓⠛⠚⠓⠛⠛⠛⠛⠋⠛⠙⠉⠉⠉⠁⠘⣷⡌⢷⣻⣿⣿⡿⠀⠀⠀⠀⢻⡆⣽⣿⠋⠙⠓⠶⠶⠿⠭⣤⢤⡤⣤⠶⠛⣻⣿
            ⣿⡟⠀⡀⠐⠠⠀⠄⡐⢀⠀⠄⡀⠄⠂⠐⡀⠀⠄⠀⠘⢿⣾⣿⣿⡿⠁⠀⠀⠀⠀⠘⡇⣾⡟⠀⢀⠠⠠⠀⠤⠠⠀⡄⢠⠀⠄⣃⣽⣿
            ⣿⠁⢠⠀⡅⢢⢁⠒⠤⡡⢌⠢⡁⠎⠤⠡⠄⠃⠌⠠⠀⠈⣿⣿⡿⠁⠀⠀⠀⠀⠀⠀⣿⢿⠁⠀⠂⠄⠡⣌⠂⠅⣃⠘⠤⣉⠒⡀⣿⣿
            ⡟⠀⠆⠱⣌⠢⣌⢘⠢⡑⡌⢦⠑⣊⠒⡥⢊⡑⠌⡐⠠⣼⣿⡿⠁⠀⠀⠀⠀⠀⠀⠀⢻⡇⠀⠌⡠⠉⠧⣈⠣⡙⣄⠫⡔⢆⠳⡈⣿⣿
            ⠇⡘⡈⠥⢀⠣⠌⢎⠒⣉⠲⡉⢆⡉⠦⡑⠂⠔⡈⠔⣸⣿⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠠⢀⡑⢐⠠⠃⡔⢠⠃⡜⢠⠃⢜⣿⣿
            ⡆⠐⠄⡁⢊⠐⡉⢄⠣⠄⠱⢈⠆⡘⠐⠌⠡⠒⠀⢬⣿⡿⠁⠀⠀⠀⠀⠀⠀⠄⠀⠀⢘⡏⠀⠀⠂⠠⠈⠄⠑⡈⠄⠒⡈⠤⠈⣸⣿⣿
            ⢹⡀⠐⠀⠂⡐⠠⢈⠐⡈⢁⠂⡐⢀⠃⠈⠄⠁⢢⣾⡟⠀⠀⠀⠀⠠⠔⠆⠀⠀⢀⠄⡾⠁⠀⠀⠐⠀⠐⠈⠀⠐⠈⠐⠀⠂⣰⢻⣿⣿
            ⠈⢷⠀⠀⠐⠀⠀⠄⠂⠀⠠⠐⠀⠀⠠⠀⠀⢀⣾⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠌⠀⣰⠁⣿⣿⣿
            ⠀⠸⡆⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⠁⣼⣿⣿⣿
            ⣦⠀⢳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠃⣼⣿⣿⣿⣿
            ⣿⡆⠀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠃⣼⣿⣿⣿⣿⣿
            ⣿⣿⡀⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠤⠤⠤⠤⠤⠤⠤⠤⠤⠤⠠⠄⠤⠤⠰⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⣿⣿⣿⣿⣿⣽
            ⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⣠⣾⣿⣿⣿⣿⣿⣟⣿
            $reset
        """.trimIndent())

        "hinata" -> println("""
            $favoriteColorUser
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠿⡷⠋⣉⡈⠛⣿⣿⡿⠟⠿⠟⠛⣿⡿⠛⠛⠛⢿⣿⡿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠠⣿⡶⣦⣿⠏⠀⠀⠀⠀⣼⡿⠀⢀⠀⠀⠀⣿⡇⠀⠀⢹⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⣿⣶⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣤⣸⣿⡇⣸⡇⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⡇⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣧⣀⢸⣿⠉⠛⢿⣿⣿⣷⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣄⠀⠀⢻⣿⣿⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣤⠘⣿⣿⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢾⣿⣿⣿⡟⣿⣿⣿⡏⣿⣿⣿⣿⣿⣿⣿⢸⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣿⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠟⠣⠤⠛⢛⣛⠿⠯⣟⠿⠿⣿⡇⢸⣿⣿⣿⣿⣿⠃⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢂⣼⡏⠀⢀⣈⣭⣭⣴⢿⡶⠀⠀⠀⠀⠀⠈⠉⠙⣻⣿⣟⡀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢻⡇⠘⢿⠃⠀⠀⠈⡷⠅⠀⠀⠀⠀⠀⠀⠀⠠⣶⣛⠻⢯⣛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⡇⠀⠸⢦⣤⣄⣰⠇⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⠛⠶⣦⣌⠁⠹⣿⣿⣿⣿⣿⣿⡿⠋⠁⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠐⠶⢶⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡃⠀⠀⠀⣽⡯⢀⣿⣿⣿⣿⣿⡏⠀⠀⠀⠀
            ⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠓⠲⠤⠴⠃⠀⢸⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀
            ⠀⠋⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠲⠶⣄⡀⠀⠀⣿⣿⣿⣟⣏⡀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣿⠀⠀⠀⠀⠀⠀⠀⠀⠘⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⢸⣿⣿⡟⣉⡤⠟⢹⠀⠀⠀⠀
            ⠁⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡄⠀⠀⠀⠀⠲⢤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⠟⠋⠁⢀⣀⣼⠍⠲⣆⠀
            ⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣹⣦⠀⠀⠀⠀⠀⠀⠉⠉⠉⠉⠉⠀⠀⠀⠀⠀⢀⣴⣿⣿⡯⠴⠒⠉⠉⠀⠀⠀⠀⢸⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣿⣿⣿⣿⣷⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣶⡿⣿⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⢠⡟⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⠶⠁⠈⢿⣿⣿⣿⠿⣦⣉⣳⢤⣀⣀⣀⣀⣤⡤⢖⣞⣯⠟⠁⣰⠏⠀⠀⣀⡠⠴⠒⠃⠀⠀⠀⠿⢤⡀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣼⣿⣿⣿⣿⣾⣿⣾⣿⣻⣿⣿⣿⣭⣿⣭⠞⠁⠀⠰⠃⠀⠔⠋⠁⠀⠀⠀⠀⢀⣀⣀⠀⣰⠇
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠺⣭⣿⠀⠈⠉⠙⠛⠛⠟⠻⠿⠿⠿⠽⢿⡿⠃⠀⠀⠀⠀⠀⠀⠀⠀⣀⠤⠶⠚⠉⠉⠓⠾⣾⡁⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⢰⣿⠀⠀⣠⣾⣻⣿⣷⠚⠃⠀⠀⣰⠋⠀⠀⠀⠀⠀⢀⣠⠔⠚⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⢱⡀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⠀⢾⣿⣿⣻⣄⣽⡇⠀⣠⠞⠀⠀⠀⠀⠀⣠⠔⠉⠀⠀⠀⢀⣠⠴⠒⠋⠉⠉⠉⠉⠳⣤⡇
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⢛⣼⣿⣶⣄⣈⠉⠛⠛⢉⣠⠞⠁⠀⠀⠀⢀⡴⠋⠁⠀⠀⠀⢀⠔⠋⠀⠀⠀⠀⠀⣀⣤⣤⣄⠘⣿
            ⡀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣾⣿⣿⠿⠋⠁⣿⣿⣿⣿⣿⣿⠟⠁⠀⠀⠀⢀⡴⠋⠀⠀⠀⠀⠀⡴⠋⠀⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⡇⡇
            ⣤⣀⡀⠀⠀⠤⠀⠴⠶⠿⠛⠉⠁⠀⠀⣿⣤⣿⣿⣿⣿⠟⠁⠀⣀⡀⢀⡴⡿⠀⠀⠀⠀⠀⣠⠞⠀⠀⠀⠀⠀⢀⣼⣿⣿⣿⡏⢸⣿⠏⢳
            ⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⣀⣠⠴⠚⡩⣾⣿⡿⠋⠁⠀⠀⢠⣿⠿⠋⣸⠁⠀⠀⠀⠀⣴⠃⠀⠀⠀⠀⠀⢀⣾⣿⣿⣿⠟⣠⠾⣿⣿⢸
            ⠶⠒⠒⠒⠲⢾⠶⠒⣺⠟⠉⠀⠀⠀⣠⣽⣞⠋⠉⢳⣀⡤⠞⢹⢻⠀⠀⡇⠀⠀⣰⠁⡼⠁⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⠗⡾⣁⢠⣿⣿⠇
            ⠀⠀⠀⠀⠀⠀⠀⢸⣇⠀⠀⠀⠀⠀⢧⣼⡿⠶⠒⠚⠳⡄⠀⢸⡘⠀⢸⠁⠀⢠⢃⡞⡁⠀⠀⠀⠀⠀⢀⣼⣟⣿⣿⠏⠀⢉⣡⣾⣿⠏⠀
            $reset
        """.trimIndent())

        "itachi" -> println("""
            $favoriteColorUser
            ⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠀
            ⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀
            ⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠯⠟⠚⢻⣿⣿⣿⣿⣿⣿⣿⡟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⡇
            ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠿⠟⠛⠋⠉⠁⠀⣠⡆⠀⠈⣿⣿⣿⣿⣿⣿⣿⡇⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⢁
            ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⣠⡶⠾⠻⠶⠟⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⡗⠺⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠈
            ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⣰⠏⢠⣶⠶⢶⣄⣀⣠⣤⣼⣿⢿⣿⣿⣿⣿⡇⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡅⠀⠀
            ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⣀⣠⣿⣤⣿⣷⣶⣿⢿⡟⠉⠁⠀⣿⠀⣿⣿⣿⣿⠇⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⡀⠀
            ⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢿⣿⠛⠛⠛⣿⠟⢿⣌⣙⣉⣡⡾⠁⠀⠀⠀⣿⠀⢸⣿⣿⣿⠀⢀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠠⠁⢠
            ⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠸⣿⠀⠀⠀⠛⠓⠛⠛⠛⠉⠁⠀⠀⠀⠀⠀⣟⣀⣠⣿⣿⣿⣿⡿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠂⠀⣼
            ⢸⣿⣿⣿⣿⣿⣿⣿⣿⣇⠀⣿⡀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣠⣤⣴⣶⣿⡿⠟⠛⣿⣿⣿⡥⣒⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⢠⣿
            ⠸⣿⣿⣿⣿⣿⣿⣿⣿⣏⣀⣸⣧⣤⣤⣤⣶⣶⣾⡿⠿⠟⠛⠛⠉⠁⠀⢀⡤⠚⢹⣿⣿⡟⣵⡟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⣾⣿
            ⠀⣿⣿⣿⣿⣿⣿⣿⣿⡿⠿⢿⣿⠿⠿⠭⣉⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⡿⠀⠰⢋⣿⡟⢣⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣿⣿
            ⠀⢸⣿⣿⣿⣿⣿⣿⣿⡇⣀⠴⢛⣂⣀⣄⣚⡒⠠⠌⠉⠢⣀⠀⠀⠀⠀⡇⢸⠀⠈⣿⡟⠛⠉⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⠀⠸⣿⣿⣿⣿⣿⣿⣿⣯⣤⡞⠋⡟⢰⣿⣻⣝⣿⡶⠄⢲⡈⠀⠀⠀⠀⡇⠈⠢⡀⢹⡇⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⣿⣿⣿
            ⠀⠀⣿⣿⣿⣿⣿⣿⣿⡇⠈⠳⣄⠘⣾⣛⣿⣿⠿⠛⠉⡸⠁⠀⠀⠀⠀⢠⠀⠀⠙⢾⡇⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣏⣼⣿⣿
            ⠀⠀⢹⣿⣿⣿⣿⣿⣿⡇⠀⠀⠚⠋⠹⠛⠉⠀⠀⠀⢰⠃⠀⠀⠀⠀⠀⠸⠀⠀⠀⠸⠓⢄⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⠀⠀⢸⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⣄⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠀⢠⠃⠀⠀⠀⠀⠀⡀⠀⠀⠀⣀⠄⠀⠀⠀⠀⠀⢸⣿⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⠀⠀⠀⢸⣿⢿⣿⣿⣿⣿⣿⣿⣷⡀⠀⠀⠀⠀⡸⠀⠀⠀⠀⠀⠀⠉⠒⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡟⣿⡄⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⠀⠀⠀⠘⣿⣸⣿⣿⣿⣿⡿⣿⣧⣛⡄⠀⠀⢀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⣿⣿⡄⠘⣿⣿⣿⣿⣿⣿⣿⣿
            ⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⡇⣿⣷⡿⣹⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⠠⠤⠒⠒⠒⠈⠀⡜⠀⣿⣿⣿⡄⠹⣿⣿⣿⣿⣿⣿⣿
            ⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⢻⣿⣷⣿⣿⣷⣄⡀⠀⠀⠀⠀⠀⠀⠐⠉⠁⣀⣀⠤⠄⠒⠃⠀⠀⠃⠀⣿⣿⣿⣷⠀⢿⣿⣿⣿⣿⣿⣿
            ⠀⠀⠀⠀⠈⣿⡍⣿⣿⢻⣿⣿⠇⣾⣿⣿⣿⣿⣿⠢⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣇⠈⣿⣿⣿⣿⣿⣿
            ⠀⠀⠀⠀⠀⢻⡇⣿⣿⠘⣿⣿⢰⣿⣿⣿⣿⣿⣿⡇⠀⢻⢢⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣞⣿⣿⣿⢿⣿⡆⠸⣿⣿⣿⣿⣿
            ⠀⠀⠀⠀⠀⢸⠗⠿⣿⣦⢹⡏⠰⣿⣿⣿⣿⣿⣿⣷⠀⠈⣆⡿⣛⢑⠦⣄⣀⠀⠀⠀⠀⣀⡴⣞⣿⡻⢿⣿⡿⣺⢵⠿⡄⢻⣿⣿⣿⣿
            $reset
        """.trimIndent())

        "pain" -> println("""
            $favoriteColorUser
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠀⠀⠀⣀⠴⢺⠃⠀⠀⢀⡠⢤⡖⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⢀⡀⠀⠀⢠⠊⡏⢀⡠⠊⠁⢠⡣⠤⠒⠉⠀⡰⠋⡀⠤⠒⢊⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⡜⡇⠀⡰⠁⠀⠷⠋⠀⠀⠀⠀⠀⠀⠀⠀⠚⠉⠁⠀⠀⣠⣋⣀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⢰⠀⢰⡜⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠤⠊⠁⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡚⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⢠⣀⠀⢰⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠓⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠹⡁⠚⠀⠀⠀⠀⢀⣠⣽⣄⡀⠀⠰⡄⠀⠀⠘⣄⠀⠀⠀⠀⡀⠀⠀⠀⠀⢀⣀⡀⠙⠢⡀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠑⡄⠀⣠⡴⠞⠛⠉⠉⢻⣿⣦⡀⠹⣦⣀⠀⠹⣷⣄⡀⠀⢳⡀⠀⠀⠀⠀⠳⡈⠉⠐⠚⠆⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⢸⠿⢁⠀⣀⡠⠤⠒⠛⣿⣿⣿⣦⣝⣿⣷⣤⡹⣿⣿⣶⣤⣿⣦⡀⠀⢶⢤⡑⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⢀⣧⣸⡿⡟⠁⠀⠀⢀⣐⣻⣿⣿⣿⡿⣿⠿⠟⠛⣿⣿⣿⣿⣿⠿⣿⣦⡘⡄⠈⠛⠆⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⡿⠏⠁⢀⣠⣴⣾⣿⣿⡛⠋⠉⠀⠀⠘⡆⠀⢰⠁⣾⣿⡈⣿⡇⠀⠱⡈⠳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⢡⣠⣴⣾⠿⣻⢿⣻⣿⢟⡇⠀⠀⠀⠀⠸⣦⣸⠀⢙⣿⠇⣾⡇⠀⠀⠱⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠈⠻⣿⣁⠀⣁⢻⣿⠿⠟⠀⠀⠀⠀⠀⠀⠉⢿⠀⠈⠉⢸⡟⠁⠀⠀⢲⢷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠸⣿⠈⣿⡅⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣶⡾⢇⠀⠀⠀⡈⢆⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⡝⠀⠿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠇⠘⡄⠀⠀⢻⠻⠄⢀⣀⣀⣠⣤⡄⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⢰⡀⣀⠤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⡿⠀⢀⣱⣤⣴⣾⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠉⢻⡆⠀⢀⣀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣰⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⢳⣊⠥⣤⠄⠀⠀⢀⣠⣤⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠱⡄⣉⣤⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀
            ⠀⠀⠀⠀⢀⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀
            ⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣤⠀⠀
            ⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀
            ⠀⠀⠀⠀⠀⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷
            $reset
        """.trimIndent())

        "minato" -> println("""
            $favoriteColorUser
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠙⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠑⠠⡀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⡀⠄⠐⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠑⠄⡀⠀⠀⠀⠀
            ⠀⠀⠀⠉⠒⢌⣡⠀⠄⢂⠒⢠⠠⠂⠀⠔⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠲⡠⠄⡀⠀⠀⠀⠈⠠⢀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠙⠢⣔⡌⠊⠀⠀⡐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⣰⠀⠀⠀⠀⠀⢀⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⢀⠐⡄⠀⠘⠴⠲⠕⠈⠒⠒⠒⠀⠁⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⡠⠂⠀⡀⠀⠠⠀⠀⠀⠄⠀⠀⠀⠀⠀⢀⡞⡁⠀⠀⠀⠀⢰⠹⠀⠀⠀⠀⠀⡜⡍⡆⠀⠀⠀⢀⡄⠀⠀⠀⠀⠀⠀⢂⠐⡀⠀⠈⢢⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⡠⠊⡀⢄⡜⠀⠠⠁⠀⢀⠔⡇⠀⠀⠀⠀⢠⠞⣤⡇⠀⠀⠀⠠⢱⠀⡇⠀⠀⠀⢀⣠⢖⢡⠀⠀⠀⢸⡰⡄⠀⠀⠀⠀⠀⠈⠂⠘⡤⠀⠀⠡⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⢀⠤⢂⢡⣰⠏⠀⠀⠄⠀⠀⠁⠀⠁⠀⠀⠀⣰⢃⠜⢸⠃⠀⠀⢀⠂⠘⢠⡇⠀⠀⠀⣎⠁⠈⡌⢇⠀⠀⠀⠱⠱⡀⠀⠀⠀⡀⠀⠐⡀⠘⢧⣄⠀⠑⡀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠉⠉⠉⢉⠃⠀⡀⡜⠠⠀⠀⠀⢸⠀⠀⠀⣴⣷⠚⠃⢸⠀⠀⠀⠁⠀⠀⣾⣷⠀⠀⡐⢈⠆⠀⠨⠸⡀⠀⠀⠰⢧⣷⡀⠀⠀⠦⡀⠀⠐⠀⠘⠄⠉⠂⢔⡄⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠃⢀⠜⣠⡣⠀⠀⠀⠀⢸⠀⠀⣼⣿⣿⠀⠀⢸⠀⠀⠄⠀⠀⠼⣧⣸⠀⢠⣠⠟⠀⠀⠀⢃⢇⠀⡀⠀⢀⣿⣇⠀⠀⠀⠀⢄⠀⢇⠀⠸⡀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠊⣐⠴⠚⠁⠁⠀⠀⠀⠀⠀⠀⢼⣿⣿⣿⡄⠃⢸⠀⡀⠀⢀⢀⣀⢰⢋⢠⠂⠀⠀⠀⠀⠀⠀⠎⢆⠃⠖⣸⣿⣿⣆⠀⠀⠀⠀⠀⢌⡦⣀⠱⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⡼⠊⠁⠀⠀⠸⠀⠀⠀⠀⠀⢀⠜⡼⣿⣷⣿⣿⡿⣿⡸⠿⣿⣿⡿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⢶⣾⣿⠶⢶⣿⣟⣿⡏⢂⠀⠀⠀⠀⠸⠑⠀⠑⢧⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠈⠈⠐⠀⠀⠀⣀⣒⣢⣄⣀⠠⢀⠈⠓⠄⠀⠀⠀⠀⠀⠀⠴⠛⠉⢀⣀⣄⣀⡀⠀⠀⡄⠐⠃⠀⠀⠀⠀⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠀⠀⠀⠀⠀⠀⠀⠀⠘⠀⠀⠯⠁⢰⠿⡿⡏⠓⡄⠐⠃⠀⠀⠀⠀⠀⠀⡖⠆⢀⡶⢫⡿⡿⣆⠉⢱⠀⡆⠀⠀⠀⠀⠀⠀⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠈⠇⠀⠧⠀⠑⠀⠀⠀⠀⠀⠀⠀⠎⠀⠀⠘⠀⠘⠧⠥⠇⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⢰⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⣀⡈⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠇⠀⠀⠀⠀⠠⣄⠈⡄⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⣠⣴⣿⣿⡇⠀⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⣾⡆⢁⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⢀⣴⣾⣿⣿⢿⣿⡇⣼⣿⠀⠀⠀⠀⠀⠀⠀⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢘⠀⠀⠀⠀⠀⠀⢿⣿⣾⣷⣄⡀⠀⠀⠀⠀⠀
            ⠀⣰⣿⣿⡿⣯⣿⣿⣻⣷⣿⣿⠀⠀⠀⠀⠀⠀⠀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⢺⣿⣿⡿⣿⣿⣶⣄⠀⠀⠀
            ⣾⣿⣿⣷⣿⣿⢯⣿⣟⣿⣽⢯⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠲⠀⠰⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⣸⣿⣷⣿⣿⣻⣿⣿⣿⣦⣄
            ⣿⣿⣷⣿⣻⣾⣿⣯⣿⣿⢣⡟⡇⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡇⠀⠀⠀⠀⢠⠀⣼⣿⣯⣿⣾⡿⣷⣿⢿⣿⣿
            ⣿⣿⣾⣟⣿⣽⣾⣟⣷⢫⡗⣽⡄⠀⠀⠀⠀⠀⠀⢽⢀⠀⠀⠀⠀⠀⠀⠒⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠄⠀⠀⠀⠀⠀⢠⡟⡇⠀⠀⠀⠀⢸⡄⣿⣯⣿⣷⡿⣿⣟⣿⣿⣯⣿
            ⣿⣿⣾⢿⣯⣿⣷⣿⣟⠧⣞⡵⡇⠀⡆⠀⠀⠀⠀⢸⡀⠣⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡿⣹⡃⠀⠀⠀⠀⣿⣣⢿⣿⣾⢿⣽⣿⢿⣻⣾⣿⣻
            ⠻⢿⣻⣿⣟⣷⣿⣾⣿⣹⠁⠀⢸⢠⠰⠀⠀⠀⠀⢸⣧⡀⠈⠄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⣼⣝⣻⡍⠃⠀⠀⠀⢠⠁⢰⠚⣿⣽⣿⣟⣯⣿⣿⢿⣽⣿
            ⠀⠀⠉⠙⠻⠿⣾⢿⣽⡖⠀⠀⠀⡞⡜⡀⠀⠀⠀⠘⣿⣿⣦⣀⡈⠢⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⣂⣵⣿⢎⣵⢳⠀⠀⠀⠀⢸⠀⠀⠀⣿⣿⣟⣾⣿⡿⣽⣿⣿⣻
            ⠀⠀⠀⠀⠀⠀⠀⠉⠛⢻⠀⠀⠀⠘⢣⢣⠀⡇⠀⠀⣿⣿⣿⣿⣿⣷⣤⣵⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣾⣿⣿⡏⣾⣼⢻⠀⢠⠀⠀⡞⠀⠀⠀⣿⣿⢻⣯⣷⣿⣿⣿⣾⣿
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢡⠀⠀⠀⢸⡘⠄⠱⡀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣄⡀⠀⢀⣠⣴⣿⣿⣿⣿⣿⣿⣿⠦⡽⢸⠀⡸⠀⢰⡃⠀⠀⢸⣿⣿⣿⣻⣿⢿⣽⣾⣿⣻
            ⠀⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠆⠀⠀⠀⡇⢧⠐⢱⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠇⡄⢠⠁⡇⢃⠃⠀⠀⣿⣿⣷⡿⣟⣿⡿⣟⣯⣿⣿
            ⠀⠀⠄⠀⠀⠀⠀⠀⠀⠀⠀⢂⠀⠀⠀⢹⢸⠞⠈⢃⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⡇⡌⠀⢧⡝⠀⠀⢸⣿⣿⣾⣿⢿⣟⣿⣿⢿⣯⣿
            ⠀⠀⠈⠄⠀⠀⠀⠀⠀⠀⠀⠘⡀⠀⠀⠈⡆⡇⠀⠈⢇⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⡜⠀⠀⠇⠆⠀⢀⣿⣿⣷⡿⣯⣿⣿⣻⣯⣿⣿⣻
            ⠀⠀⠀⠘⠄⠀⠀⠀⠀⠀⠀⠀⢁⠀⠀⠀⢇⢚⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⢸⡸⠀⠀⢸⣿⣷⡿⣿⣟⣿⣽⣿⣻⣯⣿⣿
            ⠀⠀⠀⠀⠘⡄⠀⠀⠀⠀⠀⠀⢰⠀⠀⠀⠸⡸⡀⠀⠀⢺⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⠀⠀⠀⢀⢇⠃⠀⢀⣿⣿⣯⣿⣿⣻⣯⣿⡿⣟⣿⠝⠁
            ⠀⠀⠀⠀⠀⢸⡄⠀⠀⠀⠀⠀⠀⡆⠀⠀⠀⣇⢣⠀⠀⠸⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⣘⠨⠀⠀⣼⣿⣯⣿⣷⡿⣟⣿⣷⡿⠋⠁⠀⠀
            ⠀⠀⠀⠀⠀⠀⠈⢄⠀⠀⠀⠀⠀⢰⠀⠀⠀⢸⠨⡀⣀⣀⠇⠀⠈⠻⢻⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⡟⠏⣇⡀⠀⢀⢇⠇⠀⢠⣿⣿⣽⣷⡿⣿⣿⠿⠋⠀⠀⠀⠀⠀
            $reset
        """.trimIndent())

        "deidara" -> println("""
            $favoriteColorUser
            ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠋⠀⠀⠀⠀⠀⠀⠀⣀⠰⣀⢂⠣⡘⣤⡬⠴⠒⠋⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠚⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⣿⣿⢿⣟⣿⣾⣿⡿⣛⣡⣤⣴⡞⠀⠀⠀⠀⠀⣠⢆⢆⡲⠜⠒⠉⠀⠀⢀⡤⢊⠭⣽⠶⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⣰⣹⠜⠉⠀⠀⠀⠀⠀⣠⠏⡰⣨⠟⢡⣢⡟⢳⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⣿⣿⣿⣿⣿⣾⣟⣯⣿⣿⠟⠀⠀⠀⠀⢀⢎⡞⠁⠀⠀⠀⠀⠀⠀⡼⢡⣒⡽⢃⢎⣾⣁⣤⣟⣩⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⣰⣋⡏⠀⠀⠀⠀⠀⠀⣀⣾⣵⣾⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⣿⣿⣿⣾⣻⡿⣿⣿⡿⠁⠀⠀⢀⣴⠹⡺⠀⠀⢀⣤⣴⣾⣿⡿⣟⣿⣻⣿⡛⢋⠉⠀⢠⠀⠀⣿⡷⢿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⣿⣿⣿⢾⣷⣿⣿⣿⠃⠀⠀⡠⠊⠀⢀⣧⣶⣿⣿⢿⣟⣯⣟⣿⢿⣽⣿⡇⡁⠀⣂⣀⣬⣤⣼⣿⣿⣶⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⣿⣟⣾⣿⣿⣿⣿⡏⠀⠀⣴⠁⠀⢰⣿⣿⣟⣷⣻⣟⣯⡷⣿⢾⣻⢿⣾⡇⠃⠀⠉⠀⠀⠀⠈⢹⡏⢿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⣿⡼⣿⡿⣿⣿⣿⠀⢀⣼⣿⠀⠀⣼⣿⡿⣼⣿⣻⢿⣼⡿⣿⣟⣿⣿⣻⣇⠇⠀⢠⠀⣀⣀⣀⣀⣿⣿⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⠈⠻⣷⢿⣿⣿⢏⣴⣿⣿⡏⠀⢠⣿⣯⣿⡷⣯⢿⣟⣾⣽⡷⣿⣞⡿⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⠀⠀⠘⣿⣿⣻⣿⣿⣿⣿⠃⠀⣸⣿⣳⣯⢿⣽⣻⣽⣞⣷⣿⣿⣿⡿⠿⠛⢛⠻⠿⠿⠿⣿⣍⠉⠉⠛⠭⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⠀⠀⠀⠈⠻⣿⣟⣯⡿⣿⠀⠀⢾⡿⣯⣟⣯⣿⣿⣿⠿⡿⠛⠉⢀⣤⡴⣶⣿⣿⡟⠳⣦⠘⡟⣳⠀⠀⠀⢻⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣯⣿⢿⣽⣿⣿
            ⣤⡄⢄⠀⠁⠈⠻⣿⡽⣾⠀⠀⣾⣿⣿⠿⠛⣏⣥⠀⢠⡇⠀⠀⠙⣿⣆⠹⡿⠿⡐⠀⠈⣧⠹⠋⠀⠀⠀⢸⡇⠀⠀⢠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣯⢟⣿⣿⣹⣿⣿
            ⣷⣄⠙⠒⢶⠀⠀⠀⠛⢷⠀⠀⡌⣟⠎⢒⢢⡈⢿⠀⢸⡀⠀⠀⠀⠈⠉⠛⠛⠿⠶⠖⠒⠋⠀⠀⠀⠀⠀⢸⣇⠀⠀⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⡿⣽⣯⢿⣯⣟⣷⣿
            ⣿⣿⣿⣤⡀⠁⠢⠤⣞⡾⠀⡼⠂⣿⢸⢿⠷⢇⠈⡇⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢘⡧⠀⢰⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣽⣻⢾⡽⣟⣾
            ⣿⣿⣿⣿⣿⣦⡄⠀⣸⠁⠌⣑⣮⣏⢰⠀⣴⢻⡆⢣⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⠀⣼⢺⡇⠀⠀⠀⠀⠀⠀⠀⢠⠀⠀⣿⣿⣿⣿⡿⣟⣯⣿⡽⣿
            ⣿⣿⣿⣿⣿⣿⣿⣮⠇⣼⠟⠉⠀⡇⢶⠘⡇⠿⡄⠘⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡶⠂⢀⣯⢠⣿⣓⣇⠀⠀⠀⠀⠀⠀⢠⣧⠀⢠⣿⣿⣿⣿⣿⣿⣿⣷⣿⣿
            ⣿⣿⣿⣿⣿⣿⣿⣷⣿⣦⡀⠀⢠⡇⠘⡄⠹⡮⠟⠀⢿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣟⡿⢸⣦⢽⠀⠀⠀⠀⠀⠀⣼⣷⠀⣼⣿⣿⢿⣿⣿⣿⣿⣿⣿⣿
            ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣥⣂⢸⠀⠀⠱⣀⢀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⠃⢹⣿⠸⡄⠀⠀⠀⠀⢸⣿⣹⠀⣿⣟⣯⣿⢿⣿⣿⣿⣿⣿⣿
            ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠹⣿⢿⢻⡿⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⣶⣶⣶⣶⣿⡄⢸⣷⣊⣇⠀⠀⠀⢀⡟⡆⣹⢀⡿⣿⣯⣿⣿⣿⣿⣿⣿⣿⣿
            ⣽⣿⣿⣿⣿⣿⣿⣯⢿⣿⣾⣿⡇⠀⠀⠀⠀⡧⢼⢸⣗⢻⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠛⠛⠛⠛⠛⠛⠛⠃⠈⣷⢉⣿⠀⠀⠀⣼⡷⣥⠣⣼⢿⣽⣻⣾⣿⣿⣿⣿⣿⣿⢿
            ⢹⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣿⡇⠀⠀⠀⠀⡿⢼⢸⡯⣓⢸⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⠿⠷⠾⠶⠀⠀⠀⣿⡥⢳⠀⠀⢰⣿⣿⢃⣳⣿⣿⣷⣿⣟⣿⣿⣿⣿⡟⣫⣟
            ⠈⠛⠛⠿⢿⣿⣿⣿⣿⣿⣿⣿⣧⣀⠀⠀⢰⠏⠶⣸⡇⠄⠖⢩⠷⣆⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡽⢺⠀⢀⣾⣿⣿⡅⣼⣿⣿⣿⣿⣿⣿⣿⣷⡿⣽⣳⣾
            ⠀⠃⡜⡒⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣬⣜⣀⢻⠆⡩⠜⢆⠩⣜⢛⠷⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣏⣟⢺⢀⣾⣿⣿⣿⣇⣿⣿⡿⣟⣷⣿⣾⣿⣿⢿⣿⣿⣿
            ⠀⠀⠀⠁⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣤⡅⢢⠁⢢⠈⡖⢠⠙⢻⣦⣤⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⡟⢻⣽⠚⣿⣿⣿⡟⣿⣿⣿⢻⣽⣿⣿⣾⣯⣿⣿⣿⣿⣿
            ⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣾⣦⣜⣠⠃⡜⢀⠣⠭⣟⠶⢦⢤⣤⣤⣄⣰⠾⠋⢄⢿⡑⣼⣿⣿⣿⣿⣿⣿⢿⣿⡹⣿⣞⣿⣾⣽⣾⣳⣿⣽
            ⠀⠀⠀⣄⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣮⣴⣅⣠⠘⡈⠆⣊⢩⣍⡯⢅⡘⠸⣼⣟⣿⣿⣿⣿⣻⣽⣿⣿⣿⣿⣯⢿⣻⣽⣿⡿⣿⣿⣿
            ⠀⠀⠀⢙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣮⣤⣒⣼⡿⣀⡰⣱⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡗⣌⢣⡓⡬⣽⣿⣽⣾⣿
            ⢀⡀⣀⠀⠚⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣚⣤⢻⡶⣱⣿⢿⡿⣿⣿
            ⠀⡙⠐⢻⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣯⣷⣿⣳⢯⡿⣽⣳⣯
            $reset
        """.trimIndent())
    }
}

// fast jeder Charakter hat seinen eigenen Song
fun songForCharacter(selectionPlayer: String) {

    when (selectionPlayer) {
        "shikamaru" -> soundThread.file = "sounds/shikamaruSong.wav"
        "tsunade" -> soundThread.file = "sounds/tsunadeSong.wav"
        "sasuke" -> soundThread.file = "sounds/sasukeSong.wav"
        "sakura" -> soundThread.file = "sounds/sakuraSong.wav"
        "kakashi" -> soundThread.file = "sounds/kakashiSong.wav"
        "jiraiya" -> soundThread.file = "sounds/jiraiyaSong.wav"
        "orochimaru" -> soundThread.file = "sounds/orochimaruSong.wav"
        "minato" -> soundThread.file = "sounds/minatoSong.wav"
        "deidara" -> soundThread.file = "sounds/deidaraSong.wav"
        "neji" -> soundThread.file = "sounds/nejiSong.wav"
        "maito gai" -> soundThread.file = "sounds/maitoGaiSong.wav"
        "naruto" -> soundThread.file = "sounds/narutoSong.wav"
        "zabuza" -> soundThread.file = "sounds/zabuzaSong.wav"
        "haku" -> soundThread.file = "sounds/hakuSong.wav"
        "kiba" -> soundThread.file = "sounds/kibaSong.wav"
        "hinata" -> soundThread.file = "sounds/hinataSong.wav"
        "rock lee" -> soundThread.file = "sounds/rockLeeSong.wav"
        "gaara" -> soundThread.file = "sounds/gaaraSong.wav"
        "pain" -> soundThread.file = "sounds/painSong.wav"
        "madara" -> soundThread.file = "sounds/madaraSong.wav"
        "asuma" -> soundThread.file = "sounds/asumaSong.wav"
    }
}

// wählt den Sound aus für Einzelcharakter oder Team
fun useSong() {

    if (characterUser.name.isNotEmpty())
        songForCharacter(characterUser.name.lowercase())
    else
        songForCharacter(mainCharacterUser.name.lowercase())
}




