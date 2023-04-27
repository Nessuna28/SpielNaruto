val white = "\u001b[0m"
val blue = "\u001b[34m"

var inputUser = ""
var selectionUser = ""

fun main() {

    println("$blue              Willkommen beim Spiel Naruto $white\n")
    Thread.sleep(3000)
    print("Möchtest du dir die Regeln anzeigen lassen? \nWähle 'ja' oder 'nein' : ")
    inputUser = readln().lowercase()

    if (inputUser == "ja")
        rules()


    selectionSelfOrRandom()

    val selectionComputer = (teamList + characterList).random()
    println("\nDu trittst an gegen: $blue${selectionComputer.uppercase()} $white")


}

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

fun selectionSelfOrRandom() {

    var counter = 0

    while (counter < 3) {
        print("\nMöchtest du selbst wählen oder per Zufallsgenerator? \n Wähle 'selbst' oder 'Zufall'. : ")
        inputUser = readln().lowercase()

        if (inputUser == "selbst") {
            val selectionTeamOrCharacter = selectionTeamOrCharacter()
            if (selectionTeamOrCharacter == "einzel") {
                selectionCharacter()
                counter = 4
            } else if (selectionTeamOrCharacter == "team") {
                selectionTeam()
                counter = 4
            }
        } else if (inputUser == "zufall") {
            selectionUser = randomGenerator().toString()
            counter = 4
        } else {
            println("Du hast eine falsche Eingabe gemacht. Versuche es nochmal.")
            counter++
        }
    }
}

fun selectionTeamOrCharacter(): String {

    var counter = 0
    var selection = ""

    println("Du hast dich entschieden selbst zu wählen. \n")

    while (counter < 3) {
        print("Wählst du ein Team oder einen einzelnen Charakter? \nWähle 'Team' oder 'Einzel'. : ")
        val inputUser = readln().lowercase()

        if (inputUser == "team") {
            selection = "team"
            counter = 4
        } else if (inputUser == "einzel") {
            selection = "einzel"
            counter = 4
        } else {
            println("Du hast eine falsche Eingabe getroffen. Versuche es nochmal!")
            counter++
        }
    }
    return selection
}

fun selectionCharacter() {

    var counter = 0

    println(
        """
                
            Die Charaktere die du zur Auswahl hast sind: $blue
            ${characterList[0]..characterList[20]})
            ${characterList[21]..characterList.last()}
            $white
        """.trimIndent()
    )

    while (counter < 3) {
        print("Für welchen Charakter entscheidest du dich? Gib den Namen ein: ")
        inputUser = readln().lowercase()
        for (character in characterList) {
            if (character.lowercase() == inputUser) {
                println("\nSuper! Du hast dich für $blue${inputUser.uppercase()} ${white}entschieden.")
                selectionUser = inputUser
                counter = 4
                break
            } else {
                println("\nDu hast eine falsche Auswahl getroffen.")
                counter++
                break
            }
        }
    }
}

fun selectionTeam() {

    var counter = 0

    println(
        """
                
            Die Teams die du zur Auswahl hast sind: $blue
            $teamList
            $white
        """.trimIndent()
    )

    while (counter < 3) {
        print("\nFür welches Team entscheidest du dich? Gib den Namen des Teamleiters ein: ")
        inputUser = readln().lowercase()
        for (team in teamList) {
            if (team.lowercase().contains(inputUser)) {
                println("\nSuper! Du hast dich für Team $blue${inputUser.uppercase()} ${white}entschieden.")
                selectionUser = inputUser
                counter = 4
                break
            } else {
                println("\nDu hast eine falsche Auswahl getroffen.")
                counter++
                break
            }
        }
    }
}

fun randomGenerator(): Any {

    val selectionFromRandomGenerator = (teamList + characterList).random()

    println("\nDu hast dich für den Zufallsgenerator entschieden. \n")

    if (selectionFromRandomGenerator.contains("Team") || selectionFromRandomGenerator.contains("und")) {
        println("Dein Team ist $blue$selectionFromRandomGenerator $white")
        selectionUser = selectionFromRandomGenerator
    } else {
        println("Dein Charakter ist $blue$selectionFromRandomGenerator $white")
        selectionUser = selectionFromRandomGenerator
    }
    return selectionFromRandomGenerator
}


