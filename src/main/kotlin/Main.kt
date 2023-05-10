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
val blackBackground = "\u001b[40m"


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
val soundThread = SoundThread("sounds/tsunadeSong.wav")

var counterRounds = 0
var counterWins = 0


fun main() {

    val game = Thread {

        greeting()
        do {
            selectionTeamOrCharacter()
            useSong()
            soundThread.start()
            characterComputer()
            valueOfCharacterPrint()
            do {
                selectionAttackUser()
                grafikForAttack()
                attackComputer()
                selectionAttackTeamComputer()
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


// der Spieler wird begrüßt und gefragt, ob er die Regeln hören möchte
// ist die Antwort ja werden ihm die Regeln angezeigt, andernfalls nicht
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

    favoriteColorUser()

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
fun favoriteColorUser() {

    var counter = ""

    do {
        println("""
        
        Welche ist deine Lieblingsfarbe?
        ${red}rot$reset, ${green}grün$reset, ${yellow}gelb$reset, ${blue}blau$reset, ${magenta}magenta$reset, ${cyan}cyan $reset
    """.trimIndent())
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
            counter = color
        }
    } while (counter != color)

}

// der Spieler wird gefragt, ob er die Regeln hören möchte
fun askListenRules() {

    Thread.sleep(1000)
    print("\nHallo $favoriteColorUser${nameUser}$reset, möchtest du dir die Regeln anzeigen lassen? \nWähle 'ja' oder 'nein' : ")
    selectionUserString = readln().lowercase()

    if (selectionUserString == "ja")
        rules()
}

// die Regeln des Spiels
fun rules() {

    println(
        """
        Zuerst darfst du dir aussuchen ob du selbst deinen Charakter bestimmst oder per Zufall.
        Wenn du dir selbst deinen Charakter aussuchen möchtest wirst du gefragt ob du dir ein Team oder einen einzelnen Charakter 
        aussuchen möchtest.
        Dann darfst du noch entscheiden ob du dir deinen Gegner aussuchen möchtest oder ob du einen zufälligen Gegner bekommst.
        Wenn du deine Auswahl getroffen hast wird gekämpft.
        Je nach dem für was du dich entschieden hast beeinflusst es die Anzahl der Züge pro Runde. Hast du dich für ein Team entschieden
        greift jedes Teammitglied in einer Runde an, hast du nur einen einzelnen Charakter greifst du auch nur einmal an.
        Dieses gilt natürlich auch für den Gegner (in dem Fall der Computer).
        Jeder ist eine Runde dran und darf angreifen, entweder bekommt der Gegner so viel Lebenspunkte abgezogen wie
        die Attacke Schaden verursacht oder er hat den Angriff abgewehrt.
        Wer zuerst keine Lebenspunkte mehr hat verliert dieses Spiel.
        
    """.trimIndent()
    )
}

// der Spieler hat die Möglichkeit zu wählen, ob er mit einem Team spielen möchte oder mit einem einzelnen Charakter
// sofern die Eingabe nicht falsch ist, wird gleich die nächste Funktion je nach Eingabe ausgeführt
// bei falscher Eingabe hat der Spieler noch zwei Versuche
fun selectionTeamOrCharacter() {

    var counter = 0

    while (counter < 3) {
        print("\nWählst du ein Team oder einen einzelnen Charakter? \nWähle 'Team' oder 'Einzel'. : ")
        selectionUserString = readln().lowercase()

        if (selectionUserString == "team" || selectionUserString == "einzel") {
            selectionSelfOrRandom()
            break
        } else {
            println("Du hast eine falsche Eingabe getroffen. Versuche es nochmal!")
            counter++
        }
    }
}

// der Spieler hat die Möglichkeit selbst zu wählen oder per Zufallsgenerator
// je nach Eingabe wird die nächste Funktion ausgeführt
// bei falscher Eingabe hat der Spieler noch zwei Versuche
fun selectionSelfOrRandom() {

    var counter = 0
    val inputUserTeamOrCharacter = selectionUserString

    while (counter < 3) {
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
            counter = 4
        } else if (selectionUserString == "zufall") {
            println("\nDu hast dich für den Zufallsgenerator entschieden. \n")
            if (inputUserTeamOrCharacter == "einzel") {
                randomGeneratorForOneCharacter()
                break
            }else if (inputUserTeamOrCharacter == "team"){
                randomGeneratorForTeam()
                break
            }
            counter = 4
        } else {
            println("Du hast eine falsche Eingabe gemacht. Versuche es nochmal.")
            counter++
        }
    }
}

// dem Spieler werden die vorhandenen Charaktere angezeigt und er darf sich, per Eingabe, einen aussuchen
// Spieler hat wieder drei Versuche für die Eingabe
// bei drei falschen Eingaben, wird dem Spieler per Zufallsgenerator ein Spieler ausgesucht
fun selectionCharacter() {

    var counter = 0

    println(
        """
            Die Charaktere die du zur Auswahl hast sind: $favoriteColorUser
            ${characterNameList.slice(0..5)}
            ${characterNameList.slice(6..9)}
            ${characterNameList.slice(10..14)}
            ${characterNameList.slice(15..15)}
            ${characterNameList.slice(16..20)}
            $reset
        """.trimIndent()
    )

    while (counter < 3) {
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
            println("\nDu hast eine falsche Auswahl getroffen.")
            counter++
        }
        println("Da du keine richtige Auswahl getroffen hast, wird dir ein zufälliger Charakter zugewiesen.")
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
            ${characterNameList.slice(6..9)}
            ${characterNameList.slice(10..14)}
            ${characterNameList.slice(15..15)}
            ${characterNameList.slice(16..20)}
            $reset
        """.trimIndent()
    )

    while (counter < 3) {
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
            println("\nDu hast eine falsche Auswahl getroffen.")
            counter++
        }
        if (counter == 3) {
            println("Da du keine richtige Auswahl getroffen hast werden dir 3 zufällige Charaktere zugewiesen.")
            randomGeneratorForTeam()
        }
    }

    selectionMainCharacter()
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
}

// die Auswahl für den Computer wird per Zufallsgenerator, je nachdem ob Team oder einzelner Charakter, getroffen
// es kommt kein Charakter doppelt in einem Team vor
fun characterComputer(){

    if (characterUser.name.isEmpty()){
        val listOfCharactersForRandom = mutableListOf<String>()

        while (listOfCharactersForRandom.size < 3) {
            val randomCharacter = characterNameList.random()
            if (!listOfCharactersForRandom.contains(randomCharacter)) {
                listOfCharactersForRandom.add(randomCharacter)
                setCharacterForComputer(randomCharacter)
                teamComputer.add(characterComputer)
            }
        }
        Thread.sleep(2000)
        println("\nDu trittst an gegen: $blue${listOfCharactersForRandom.toString().uppercase()} $reset")
    } else if (characterUser.name.isNotEmpty()){
        val selection = characterList.random()
        characterComputer = selection
        Thread.sleep(2000)
        println("\nDu trittst an gegen: $blue${characterComputer.name.uppercase()} $reset")
    }
}

// diese Funktion zählt die Siege und gibt sie in einer Println aus
fun newRoundOrNotAndCountRoundsWon() {

    var winsRoundOrRounds = ""
    var roundOrRounds = ""

    if (counterWins == 1 )
        winsRoundOrRounds = "Runde"
        else
            winsRoundOrRounds = "Runden"

    if (counterRounds == 1)
        roundOrRounds = "Runde"
        else
            roundOrRounds = "Runden"


    print("\nMöchtest du noch eine Runde spielen? 'Ja' oder 'nein': ")
    selectionUserString = readln().lowercase()


    if (selectionUserString == "ja") {
            println(
                """
            
            Super, du möchtest noch eine Runde spielen.
            Du hast ${favoriteColorUser()}$counterWins $winsRoundOrRounds ${reset}von $counterRounds $roundOrRounds gewonnen. 👏
            
            Auf gehts in eine neue Runde! 🤗
        """.trimIndent()
            )

    } else if (selectionUserString == "nein") {
            println("""
            
            Ok, du möchtest keine Runde mehr spielen.
            Du hast ${favoriteColorUser()}$counterWins $winsRoundOrRounds ${reset}von $counterRounds $roundOrRounds gewonnen. 👏
            
            Bis nächstes Mal! 👋
        """.trimIndent())
    }
}

// diese Funktion nimmt die Eingaben vom Typ String und sucht sie in der Charakterliste und
// speichert den Charakter vom Typ Character, CharacterWithGenjutsu oder CharacterWithMedicalSkills in der Variablen characterUser
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
            } else {
                if (character.name.lowercase() == string) {
                    characterUser = character
                }
            }
        }

    if (inputList.size >= 3)
        characterUser.name = ""
}

// diese Funktion nimmt die Eingaben vom Typ String und sucht sie in der Charakterliste und
// speichert den Charakter vom Typ Character, CharacterWithGenjutsu oder CharacterWithMedicalSkills in der Variablen characterComputer
fun setCharacterForComputer(string: String) {

    for (character in characterList) {
        if (character is CharacterWithMedicalSkills) {
            if (character.name.lowercase() == string) {
                characterComputer = character as CharacterWithMedicalSkills
            }
        } else if (character is CharacterWithGenjutsu) {
            if (character.name.lowercase() == string) {
                characterComputer = character as CharacterWithGenjutsu
            }
        } else {
            if (character.name.lowercase() == string) {
                characterComputer = character
            }
        }
    }
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

    if (mainCharacterUser.name.isNotEmpty())
        songForCharacter(mainCharacterUser.name.lowercase())
}




