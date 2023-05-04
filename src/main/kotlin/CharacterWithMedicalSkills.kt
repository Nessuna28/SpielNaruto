class CharacterWithMedicalSkills: Character {

    var medicalSkills: Boolean

    constructor(name: String, attack: MutableMap<String, Int>, ninjutsu: MutableMap<String, Int>, weapon: MutableMap<String, Int>, medicalSkills: Boolean):
            super(name, attack, ninjutsu, weapon){

        this.medicalSkills = medicalSkills
    }

    // diese Funktion ermöglicht es dem Spieler sich zu heilen in dem seinen lifePoints 100 dazu gerechnet werden
    // aber mehr als 200 (Anfangswert) gehen nicht
    // diese Fähigkeit verbraucht Chakra
    fun heal(){

        this.lifePoints += 100
        if (lifePoints > 200){
           lifePoints = 200
        }

        coloredBar()
        println("\nDu wurdest geheilt!")

        lostChakra(20)
    }

    // in der Funktion wird ein farbiger Balken generiert für die Funktion heal
    fun coloredBar(){

        var coloredBar = StringBuilder()
        coloredBar.append("|")

        val life = lifePoints / 100
        val to = life + 10

        for (point in 0..life){
            coloredBar.append("$blueBackground |")
        }
        Thread.sleep(2000)

        for (point in life..to){
            print("\r$greenBackground $coloredBar$white")
            coloredBar.append(" |")
            Thread.sleep(800)
        }

        for (point in to..20){
            print("\r")
        }
    }

    // die Funktion aus Character um die Möglichkeit der Heilung erweitert
    override fun showSelection() {

        var counter = 0

        do {
            println(
                """
                    
            Womit möchtest du angreifen?
            1 für Taijutsu
            2 für ein Ninjutsu
            3 für eine Waffe
            4 für Heilung
        """.trimIndent()
            )
            print("Gib die jeweilige Zahl ein: ")
            inputUserInt = readln().toInt()

            if (inputUserInt == 1) {
                println("\nDas hast du zur Auswahl:")
                var index = 1
                for (attack in attack.keys) {
                    println("$index für $attack")
                    index++
                }
                print("Triff deine Auswahl per Zahl: ")
                inputUserInt = readln().toInt()
                characterUser.attackNormal(inputUserInt, characterComputer)
                counter = inputUserInt
            } else if (inputUserInt == 2) {
                println("\nDas hast du zur Auswahl:")
                var index = 1
                for (attack in ninjutsu.keys) {
                    println("$index für $attack")
                    index++
                }
                print("Triff deine Auswahl per Zahl: ")
                inputUserInt = readln().toInt()
                characterUser.attackWithNinjutsu(inputUserInt, characterComputer)
                counter = inputUserInt
            } else if (inputUserInt == 3) {
                println("\nDas hast du zur Auswahl:")
                var index = 1
                for (attack in weapon.keys) {
                    println("$index für $attack")
                    index++
                }
                print("Triff deine Auswahl per Zahl: ")
                inputUserInt = readln().toInt()
                characterUser.attackWithWeapon(inputUserInt, characterComputer)
                counter = inputUserInt
            } else if (inputUserInt == 4){
                println("Möchtest du Heilung anwenden?")
                print("Gib ein ja oder nein: ")
                inputUserString = readln().lowercase()
                if (inputUserString == "ja"){
                    heal()
                    counter = inputUserInt
                } else {
                    continue
                }
            } else {
                println("Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                counter = 0
            }
        } while (counter != inputUserInt)
    }


}