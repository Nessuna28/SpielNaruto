fun teamFight() {}

var mainCharacter = Character("", mutableMapOf(), mutableMapOf(), mutableMapOf())

// Spieler soll sich aus dem Team einen Hauptcharakter aussuchen
fun selectionTeamFight() {

    println("Welcher soll dein Hauptcharakter sein?")
    print("Gib den Namen ein! : ")
    val selectionUser = readln().lowercase()
    setCharacterForTeam(selectionUser)
    teamUser.remove(mainCharacter)
}

// diese Funktion nimmt die Eingaben vom Typ String und sucht sie in der Charakterliste und
// speichert den Charakter vom Typ Character, CharacterWithGenjutsu oder CharacterWithMedicalSkills in der Variablen mainCharacter
fun setCharacterForTeam(string: String) {

    for (character in characterList) {
        if (character is CharacterWithMedicalSkills) {
            if (character.name.lowercase() == string) {
                mainCharacter = character as CharacterWithMedicalSkills
            }
        } else if (character is CharacterWithGenjutsu) {
            if (character.name.lowercase() == string) {
                mainCharacter = character as CharacterWithGenjutsu
            }
        } else {
            if (character.name.lowercase() == string) {
                mainCharacter = character
            }
        }
    }
}