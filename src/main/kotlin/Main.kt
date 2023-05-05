val white = "\u001b[0m"
val magenta = "\u001b[35m"
val blue = "\u001b[34m"
val green = "\u001b[32m"
val greyBackground = "\u001b[47m"
val greenBackground = "\u001b[42m"
val blueBackground = "\u001b[44m"
val blackBackground = "\u001b[40m"


// diese Variablen sind außerhalb der Main, sodass ich von überall etwas in ihnen abspeichern kann
// und nach Belieben aufrufen kann ohne ständig zwischendurch viele Variablen anlegen zu müssen
var selectionUserString = ""
var selectionUserInt = 0
var characterUser = Character("", mutableMapOf(), mutableMapOf(), mutableMapOf())
var teamUser = mutableListOf<Character>()
var characterComputer = Character("", mutableMapOf(), mutableMapOf(), mutableMapOf())
var teamComputer = mutableListOf<Character>()
var selectionComputer = ""

fun main() {

    greeting()

    selectionTeamOrCharacter()
    characterComputer()
    do {
        selectionAttackUser()
        attackComputer()
        if (selectionComputer != "Baumstamm") {
            println("\nDu hast mit $selectionUserString angegriffen und getroffen. \nDer Computer hat noch ${characterComputer.lifePoints}/500")
            println("\nDer Computer hat mit $selectionComputer angegriffen. Du hast noch ${characterUser.lifePoints}/500")
        } else
            println("\nDer Computer hat $selectionComputer angewendet.")
    } while (characterComputer.lifePoints > 0 || characterUser.lifePoints > 0)
}


// der Spieler wird begrüßt und gefragt, ob er die Regeln hören möchte
// ist die Antwort ja werden ihm die Regeln angezeigt, andernfalls nicht
fun greeting() {

    println("$blue              Willkommen beim Spiel Naruto")
    println(
        """
       ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠖⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠤⠀⠉⠁⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠑⠲⢤⣀⡀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠓⠦⣄⡀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠋⠀⠀⠀⠀⠀⢀⠀⠀⠀⠰⡀⠀⠀⠀⣀⣀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠠⡀⠀⠀⠀⠀⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠴⠛⠁⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⣀⠔⠉⠀⠀⠀⠀⠀⠀⠀⠀⢳⣆⡀⠀⠙⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣤⣻⡀⠀⠀⠀⠀⠀⣦⠀⠀⠀⠀⠀⣠⡴⠒⠉⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⣠⠞⠁⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣦⣀⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⠀⠀⠀⠀⣿⠀⠀⠀⠀⢀⡙⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠺⠓⠊⠉⠉⣲⠀⠈⡇⠀⠀⠀⠀⠀⣿⣿⡉⠉⠉⠑⠦⣳⡄⠀⠀⠉⠉⠉⠉⠛⠻⠿⠿⣿⣿⣿⣿⡄⠀⠀⠀⣿⠀⠀⠀⠀⠀⢻⠲⠿⣆⡀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠏⠀⣴⣿⡄⠀⠀⠀⢀⡏⠛⠃⠀⠀⠀⠀⠀⠙⠀⣠⡤⣤⣀⣠⠦⠀⠀⠀⠀⠀⣍⠻⣿⣄⠀⠀⢻⠀⠀⠀⠀⠀⠀⢇⠀⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⡸⠀⣼⣿⣿⣷⡀⠀⠀⢸⣇⠀⠀⠀⠀⠠⠄⠀⣰⡟⢉⣤⣤⡉⠁⠀⠀⠀⠀⠤⠀⠙⠁⢹⣿⣆⠀⢸⣧⡀⠀⠀⠀⠀⠸⡆⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⢰⡇⣴⣿⣿⣿⣿⣧⡀⠀⢘⡟⠁⠀⠀⠀⠀⢀⡾⢻⡄⠸⣤⣌⣿⠀⠀⠀⠀⡄⠀⠀⣤⠀⣿⣿⣿⣆⢸⣿⣿⣦⡀⠀⠀⠀⢹⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⣾⡜⢹⣿⣿⣿⣿⣿⣷⡀⠐⣧⡀⠀⢀⣴⠀⠈⠙⠲⠿⠶⠴⠾⠋⠀⠀⠐⠺⠆⠀⠀⠈⠀⣿⣿⣿⣿⣾⣿⣿⣿⣟⢦⡀⠀⠀⣇⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠟⠁⢸⣿⣿⣿⣿⣿⣿⣷⡄⣟⣁⣀⣀⣠⣤⣤⣤⣤⣤⣄⣀⣀⣀⣀⣀⣀⠀⠀⠀⣴⡖⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⢹⢦⡀⢸⡄⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⠿⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣦⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⣸⠀⠙⢦⣧⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢼⣿⣿⡿⢻⡏⠉⠉⠈⠙⠲⢬⣗⡦⣄⠀⠀⠀⣠⠄⠀⠀⣹⠉⠉⢉⣟⣿⠟⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣆⣀⣠⣽⣦⣤⣤⣴⣶⣶
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⡛⡄⠀⣸⠀⠀⣴⠞⢻⡽⢿⣾⣍⢾⣿⣦⠞⠁⠀⠀⠀⢿⣤⣶⡾⢛⣾⣿⣷⠶⣄⡀⠈⠉⢹⠙⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣇⣷⣿⠀⣿⠀⠀⢿⡄⢿⡴⢆⡷⠹⣎⢻⠅⠀⠀⠀⠀⢀⣴⣿⠏⣴⠏⣾⢰⠎⣳⢀⣿⠂⠀⢸⠀⠀⣾⣹⡟⢹⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡏⢱⣿⡇⡟⠀⠀⠈⠳⢤⣉⣉⣤⡤⠟⠀⠀⠀⠀⠀⠀⣼⡿⠁⠀⠻⣄⣉⣓⣚⣣⡾⠃⠀⠀⢸⠀⢸⣿⠻⠃⡟⢿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣇⣿⡿⢻⡇⠀⠀⠀⠀⣀⣀⡤⠀⠀⠀⠀⠀⠀⠀⠀⢀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⢰⢿⣿⡀⣰⠃⠈⢻⣿⣷⡀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⢿⣷⡈⠃⣠⠤⠒⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⢠⡄⠰⢤⣀⠀⠀⠀⠀⢸⣿⣿⢟⣵⠏⠀⠀⠸⣿⣿⣷⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣦⠉⠙⣇⠀⠀⠀⠀⢀⣤⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢲⢤⡀⠀⠌⠙⠲⢤⡀⡼⠋⠁⣹⠋⠀⠀⠀⠀⢻⣿⣿⣄⡀⠀⣀⣀⣀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢦⠀⣿⠀⢀⡤⠞⠉⠀⠀⠀⠀⠀⠀⢀⣤⣀⢀⣀⣀⡀⠀⠀⠀⠈⠀⠈⠳⢆⡀⠀⠀⢙⣧⣀⡞⡇⠀⠀⠀⠀⠀⠈⢿⣿⣿⣿⣿⣿⡿⣿
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣈⣻⣹⣦⣉⣤⣤⣤⣾⣄⠀⠀⠀⠀⠈⠻⣽⣵⣟⡿⠃⠀⠀⠀⠀⠀⢧⡀⠀⠙⢦⣠⠟⢀⡿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿⣿⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⡇⠀⠀⠀⠀⠀⠈⠛⠋⠀⠀⠀⠀⠀⠀⠀⠀⠙⢆⠀⢠⡟⠀⣸⡁⢸⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣿⣿⣦⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⠲⠶⣶⣉⣉⣉⣉⣉⢉⣉⣭⠭⠟⠛⠀⠀⠀⠈⢳⡟⣶⠋⢸⣿⣿⣿⣿⣶⣶⣤⠀⠀⠀⠀⠀⠀⠈⠻⢿⡄
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡏⣹⣿⣧⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⡿⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⢞⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡽⠁⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣧⡉⡳⢦⣀⣀⠀⠀⠀⠀⣀⣠⣴⣾⢟⣵⣿⣿⣺⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠃⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠊⠀⠀⠈⠉⢟⡿⣻⣿⢿⣿⣽⡷⣿⣿⢞⡵⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡎⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⢾⠂⠀⢀⢴⢵⡿⣺⢟⣷⡷⣻⣾⣾⢟⣵⠋⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡞⠁⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⢀⣀⣠⣤⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢄⣶⣿⣷⡊⢘⢼⡵⣫⣠⢾⣝⡷⡻⠃⠀⠘⣯⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣷⣤⡀⠀⠀⠀⠀⠀⠀⠀
    ⣀⣤⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠔⢹⠃⠈⠳⣞⡿⢻⠞⣵⣫⣫⡾⠁⠀⠀⠀⣸⡿⢙⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⣽⣿⣿⣿⣷⣤⣀⠀⠀⠀⠀
   
   $white""".trimIndent()
    )

    Thread.sleep(3000)
    print("Möchtest du dir die Regeln anzeigen lassen? \nWähle 'ja' oder 'nein' : ")
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
                
            Die Charaktere die du zur Auswahl hast sind: $blue
            ${characterNameList.slice(0..3)}
            ${characterNameList.slice(4..5)}
            
            $white
        """.trimIndent()
    )

    while (counter < 3) {
        print("Für welchen Charakter entscheidest du dich? Gib den Namen ein: ")
        selectionUserString = readln().lowercase()

        val lowercaseList = listToLowercaselist(characterNameList)

        if (lowercaseList.contains(selectionUserString)) {
            println("\nSuper! Du hast dich für $magenta${selectionUserString.uppercase()} ${white}entschieden.")
            setCharacterForUser(selectionUserString)
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
            ${characterNameList.slice(0..3)}
            ${characterNameList.slice(4..5)}
            
            $white
        """.trimIndent()
    )

    while (counter < 3) {
        print("Für welche Charaktere entscheidest du dich? Gib drei Namen ein und trenne sie mit Komma: ")
        selectionUserString = readln().lowercase()
        val inputList = selectionUserString.split(", ")

        val lowercaseList = listToLowercaselist(characterNameList)

        if (inputList[0] in lowercaseList && inputList[1] in lowercaseList && inputList[2] in lowercaseList) {
            println("\nSuper! Du hast dich für $magenta${selectionUserString.uppercase()} ${white}entschieden.")

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
}

// der Zufallsgenerator für einen Charakter
fun randomGeneratorForOneCharacter() {

    val characterForRandom = characterNameList.random()

    Thread.sleep(2000)

    println("Dein Charakter ist $magenta${characterForRandom.uppercase()} $white")
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
    println("Deine Charaktere sind: $magenta${listOfCharactersForRandom.toString().uppercase()} $white")
}

// die Auswahl für den Computer wird per Zufallsgenerator, je nachdem ob Team oder einzelner Charakter, getroffen
// es kommt kein Charakter doppelt in einem Team vor
fun characterComputer(){

    if (characterUser.name == ""){
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
        println("\nDu trittst an gegen: $blue${listOfCharactersForRandom.toString().uppercase()} $white")
    } else {
        val selection = characterList.random()
        characterComputer = selection
        Thread.sleep(2000)
        println("\nDu trittst an gegen: $blue${selectionComputer.uppercase()} $white")
    }
}

// diese Funktion nimmt die Eingaben vom Typ String und sucht sie in der Charakterliste und
// speichert den Charakter vom Typ Character, CharacterWithGenjutsu oder CharacterWithMedicalSkills in der Variablen characterUser
fun setCharacterForUser(string: String) {

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

// jeder Spieler hat seine eigene kleine Grafik
fun grafik(selectionPlayer: String){

    when (selectionPlayer) {
        "naruto" -> println(
            """
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
        
    """.trimIndent()
        )

        "sasuke" -> println(
            """
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
        
    """.trimIndent()
        )

        "sakura" -> println(
            """
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
        ⠀
    """.trimIndent()
        )

        "shikamaru" -> println(
            """
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
        
    """.trimIndent()
        )
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



