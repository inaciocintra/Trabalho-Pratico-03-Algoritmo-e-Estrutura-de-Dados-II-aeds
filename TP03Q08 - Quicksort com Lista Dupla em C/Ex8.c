#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>


#define FILE_PATH "/tmp/characters.csv"
#define INITIAL_STRING_CAPACITY 32
#define INITIAL_ARRAY_CAPACITY 5

typedef struct {
    char *id;
    char *name;
    char **alternateNames;
    int alternateNamesCount;
    char *house;
    char *ancestry;
    char *species;
    char *patronus;
    bool isHogwartsStaff;
    bool isHogwartsStudent;
    char *actorName;
    bool isAlive;
    char **alternateActors;
    int alternateActorsCount;
    char *dateOfBirth;
    int yearOfBirth;
    char *eyeColour;
    char *gender;
    char *hairColour;
    bool isWizard;
} Character;

void free_string_array(char **array, int length);
void free_character(Character *character);

char *read_string(int *i, const char *csvLine) {
    int maxSize = strlen(csvLine);
    if (*i >= maxSize) 
        return NULL; 

    int capacity = INITIAL_STRING_CAPACITY;
    char *str = (char *)malloc(capacity * sizeof(char));
    if (str == NULL) {
        perror("Memory allocation error in string");
        return NULL;
    }

    int pos = 0; 
    while (*i < maxSize && csvLine[*i] != ';') 
    {
        if (pos >= capacity-1) {
            capacity *= 2;
            char *temp = (char *)realloc(str, capacity * sizeof(char));
            if (temp == NULL) {
                perror("Error when resizing string");
                free(str); 
                return NULL;
            }
            str = temp;
        }
        str[pos++] = csvLine[(*i)++];
    }

    str[pos] = '\0'; 
    (*i)++;
    return str;
}

char **read_multivalued(int *i, const char *csvLine, int *arrayCount) {
    int maxSize = strlen(csvLine);
    if (*i >= maxSize-1 || csvLine[*i] != '[') 
        return NULL;

    (*i)++; // jump '['

    int arrayCapacity = INITIAL_ARRAY_CAPACITY;
    char **array = (char **)malloc(arrayCapacity * sizeof(char *)); 
    if (array == NULL) {
        perror("Memory allocation error in string array");
        return NULL;
    }

    int pos = 0;
    while (*i < maxSize && csvLine[*i] != ']')
    {
        if (pos >= arrayCapacity) {
            arrayCapacity *= 2; 
            char **temp = (char **)realloc(array, arrayCapacity * sizeof(char *)); 
            if (temp == NULL) {
                perror("Error when resizing string array");
                free_string_array(array, pos);
                return NULL;
            }
            array = temp; 
        }

        int strCapacity = INITIAL_STRING_CAPACITY;
        char *tempStr = (char *)malloc(strCapacity * sizeof(char));
        if (tempStr == NULL) {
            perror("Memory allocation error in string");
            free_string_array(array, pos);
            return NULL;
        }

        int j = 0;
        while (*i < maxSize && csvLine[*i] != ',' && csvLine[*i] != ']')
        {
            if (j >= strCapacity - 1) { 
                strCapacity *= 2;
                char *tempStr2 = (char *)realloc(tempStr, strCapacity * sizeof(char));
                if (tempStr2 == NULL) {
                    perror("Memory reallocation error in string");
                    free_string_array(array, pos);
                    free(tempStr);
                    return NULL;
                }
                tempStr = tempStr2; 
            }
            if (csvLine[*i] != '\'') 
                tempStr[j++] = csvLine[*i];

            (*i)++;
        }

        tempStr[j] = '\0'; 
        array[pos++] = tempStr;

        if (csvLine[*i] == ',') 
            (*i)++; 
    }
    (*i) += 2; // jump ']' and ';'
    *arrayCount = pos; 
    return array;
}

bool read_boolean(int *i, const char *csvLine) {
    bool predicate = false;
    char* wordRead = read_string(i, csvLine);

    if (wordRead != NULL) {
        if (wordRead[0] == 'V')
            predicate = true;
        free(wordRead);
    }

    return predicate; 
}

int read_integer(int *i, const char *csvLine){
    char* wordRead = read_string(i, csvLine);
    if(wordRead == NULL)
        return -1;
    return atoi(wordRead);
}

Character* csvLine_mapper(char *csvLine) {
    Character* character = (Character*)malloc(sizeof(Character));
    if (character == NULL) {
        perror("Memory allocation error in Character struct");
        return NULL;
    }

    int i = 0;

    character->id = read_string(&i, csvLine);
    character->name = read_string(&i, csvLine);

    int alternateNamesCount = 0;
    character->alternateNames = read_multivalued(&i, csvLine, &alternateNamesCount);
    character->alternateNamesCount = alternateNamesCount;

    character->house = read_string(&i, csvLine);
    character->ancestry = read_string(&i, csvLine);
    character->species = read_string(&i, csvLine);
    character->patronus = read_string(&i, csvLine);
    character->isHogwartsStaff = read_boolean(&i, csvLine);
    character->isHogwartsStudent = read_boolean(&i, csvLine);
    character->actorName = read_string(&i, csvLine);
    character->isAlive = read_boolean(&i, csvLine);

    int alternateActorsCount = 0;
    character->alternateActors = read_multivalued(&i, csvLine, &alternateActorsCount);
    character->alternateActorsCount = alternateActorsCount;

    character->dateOfBirth = read_string(&i, csvLine);
    character->yearOfBirth = read_integer(&i, csvLine);
    character->eyeColour = read_string(&i, csvLine);
    character->gender = read_string(&i, csvLine);
    character->hairColour = read_string(&i, csvLine);
    character->isWizard = read_boolean(&i, csvLine);

    return character; 
}

void print_string_array(char **array, int count) {
    printf("{");
    for (int i = 0; i < count; i++) {
        printf("%s", array[i]);
        if (i < count - 1) {
            printf(",");
        }
    }
    printf("}");
}

void print_character(Character *character) {
    if (character == NULL) {
        printf("Character struct is NULL.\n");
        return;
    }

    printf("[");
    printf("%s", character->id ? character->id : " "); 
    printf(" ## %s", character->name ? character->name : " ");

    printf(" ## ");
    if (character->alternateNames) 
        print_string_array(character->alternateNames, character->alternateNamesCount);
    else 
        printf(" {}");

    printf(" ## %s", character->house ? character->house : " "); 
    printf(" ## %s", character->ancestry ? character->ancestry : " "); 
    printf(" ## %s", character->species ? character->species : " "); 
    printf(" ## %s", character->patronus ? character->patronus : " "); 
    printf(" ## %s", character->isHogwartsStaff ? "true" : "false"); 
    printf(" ## %s", character->isHogwartsStudent ? "true" : "false"); 
    printf(" ## %s", character->actorName ? character->actorName : " ");
    printf(" ## %s", character->isAlive ? "true" : "false"); 
    printf(" ## %s", character->dateOfBirth ? character->dateOfBirth : " "); 
    printf(" ## %d", character->yearOfBirth); 
    printf(" ## %s", character->eyeColour ? character->eyeColour : " ");
    printf(" ## %s", character->gender ? character->gender : " ");
    printf(" ## %s", character->hairColour ? character->hairColour : " "); 
    printf(" ## %s", character->isWizard ? "true" : "false");

    printf("]\n");
}

/*  Função para particionar o array
int partition(Character **characters, int low, int high) {
    // Escolhe o último elemento como pivot
    Character *pivot = characters[high];
    int i = low - 1;

    // Realiza a comparação com o pivot
    for (int j = low; j < high; j++) {
        // Primeiro compara as casas (house)
        int cmp_house = strcmp(characters[j]->house, pivot->house);
        if (cmp_house < 0) {
            // Se house é menor, incrementa i e troca os elementos
            i++;
            Character *temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        } else if (cmp_house == 0) {
            // Se house é igual, desempate pelo nome
            int cmp_name = strcmp(characters[j]->name, pivot->name);
            if (cmp_name < 0) {
                i++;
                Character *temp = characters[i];
                characters[i] = characters[j];
                characters[j] = temp;
            }
        }
    }

    // Coloca o pivot na posição correta
    Character *temp = characters[i + 1];
    characters[i + 1] = characters[high];
    characters[high] = temp;

    // Retorna a posição final do pivot
    return i + 1;
}

// Função recursiva para aplicar o Quick Sort
void quick_sort_by_house_and_name(Character **characters, int low, int high) {
    if (low < high) {
        // Particiona o array
        int pi = partition(characters, low, high);

        // Ordena as duas metades
        quick_sort_by_house_and_name(characters, low, pi - 1);
        quick_sort_by_house_and_name(characters, pi + 1, high);
    }
} */







//Lista Dupla
typedef struct CelulaDupla {
    Character *personagem;              
    struct CelulaDupla* prox;  
    struct CelulaDupla* ant;   
} CelulaDupla;

CelulaDupla* novaCelulaDupla(Character *personagem) {
    CelulaDupla* nova = (CelulaDupla*) malloc(sizeof(CelulaDupla));
    nova->personagem = personagem;
    nova->ant = nova->prox = NULL;
    return nova;
}

CelulaDupla* primeiro;
CelulaDupla* ultimo;

// Criação do dummy com todos os atributos vazios
Character dummy = {
    .id = NULL,
    .name = NULL,
    .alternateNames = NULL,
    .alternateNamesCount = 0,
    .house = NULL,
    .ancestry = NULL,
    .species = NULL,
    .patronus = NULL,
    .isHogwartsStaff = false,
    .isHogwartsStudent = false,
    .actorName = NULL,
    .isAlive = false,
    .alternateActors = NULL,
    .alternateActorsCount = 0,
    .dateOfBirth = NULL,
    .yearOfBirth = 0,
    .eyeColour = NULL,
    .gender = NULL,
    .hairColour = NULL,
    .isWizard = false
};
/**
 * Cria uma lista dupla sem elementos (somente no cabeca).
 */
void start() {
    primeiro = novaCelulaDupla(&dummy);
    ultimo = primeiro;
}

void inserirInicio(Character *personagem) {
    CelulaDupla* tmp = novaCelulaDupla(personagem);
    tmp->ant = primeiro;
    tmp->prox = primeiro->prox;
    primeiro->prox = tmp;
    if (primeiro == ultimo) {
        ultimo = tmp;
    } else {
        tmp->prox->ant = tmp;
    }
    tmp = NULL;
}

void inserirFim(Character *personagem) {
    ultimo->prox = novaCelulaDupla(personagem);
    ultimo->prox->ant = ultimo;
    ultimo = ultimo->prox;
}

Character *removerInicio() {
    if (primeiro == ultimo) {
        printf("Erro ao remover (vazia)!");
    }
    CelulaDupla* tmp = primeiro;
    primeiro = primeiro->prox;
    Character *resp = primeiro->personagem;
    tmp->prox = primeiro->ant = NULL;
    free(tmp);
    tmp = NULL;
    return resp;
}

Character *removerFim() {
    if (primeiro == ultimo) {
        printf("Erro ao remover (vazia)!");
    } 
    Character *resp = ultimo->personagem;
    ultimo = ultimo->ant;
    ultimo->prox->ant = NULL;
    free(ultimo->prox);
    ultimo->prox = NULL;
    return resp;
}

int tamanho() {
    int tamanho = 0; 
    CelulaDupla* i;
    for(i = primeiro; i != ultimo; i = i->prox, tamanho++);
    return tamanho;
}

void inserir(Character *personagem, int pos) {
    int tam = tamanho();
    if (pos < 0 || pos > tam) {
        printf("Erro ao inserir (posicao %d/%d invalida!", pos, tam);
    } else if (pos == 0) {
        inserirInicio(personagem);
    } else if (pos == tam) {
        inserirFim(personagem);
    } else {
        CelulaDupla* i = primeiro;
        for (int j = 0; j < pos; j++, i = i->prox);
        CelulaDupla* tmp = novaCelulaDupla(personagem);
        tmp->ant = i;
        tmp->prox = i->prox;
        tmp->ant->prox = tmp->prox->ant = tmp;
        tmp = i = NULL;
    }
}

Character *remover(int pos) {
    Character *resp;
    int tam = tamanho();
    if (primeiro == ultimo) {
        printf( "Erro ao remover (vazia)!");
    } else if (pos < 0 || pos >= tam) {
        printf("Erro ao remover (posicao %d/%d invalida!", pos, tam);
    } else if (pos == 0) {
        resp = removerInicio();
    } else if (pos == tam - 1) {
        resp = removerFim();
    } else {
        CelulaDupla* i = primeiro->prox;
        for (int j = 0; j < pos; j++, i = i->prox);
        i->ant->prox = i->prox;
        i->prox->ant = i->ant;
        resp = i->personagem;
        i->prox = i->ant = NULL;
        free(i);
        i = NULL;
    }
    return resp;
}
////////////////// 
void mostrar() {
    CelulaDupla* i;

    for (i = primeiro->prox; i != NULL; i = i->prox) {
        print_character(i->personagem);
    }

}
//////////////////



/*void mostrarInverso() {
    printf("[ ");
    CelulaDupla* i;
    for (i = ultimo; i != primeiro; i = i->ant) {
        printf("%d ", i->personagem);
    }
    printf("] \n");
}*/

/*bool pesquisar(Character x) {
    bool resp = false;
    CelulaDupla* i;
    for (i = primeiro->prox; i != NULL; i = i->prox) {
        if (i->personagem == x) {
            resp = true;
            i = ultimo;
        }
    }
    return resp;
}
*/





// Função para trocar dois elementos
void swap(CelulaDupla* a, CelulaDupla* b) {
    Character *temp = a->personagem;
    a->personagem = b->personagem;
    b->personagem = temp;
}

// Função de particionamento para QuickSort
CelulaDupla* partition(CelulaDupla* low, CelulaDupla* high) {
    Character *pivot = high->personagem;
    CelulaDupla* i = low->ant;
    for (CelulaDupla* j = low; j != high; j = j->prox) {
        if (strcmp(j->personagem->house, pivot->house) < 0 || 
            (strcmp(j->personagem->house, pivot->house) == 0 && strcmp(j->personagem->name, pivot->name) < 0)) {
            i = (i == NULL) ? low : i->prox;
            swap(i, j);
        }
    }
    i = (i == NULL) ? low : i->prox;
    swap(i, high);
    return i;
}

// Função QuickSort para lista duplamente ligada
void _quickSort(CelulaDupla* low, CelulaDupla* high) {
    if (high != NULL && low != high && low != high->prox) {
        CelulaDupla* pi = partition(low, high);
        _quickSort(low, pi->ant);
        _quickSort(pi->prox, high);
    }
}

void quickSort() {
    _quickSort(primeiro->prox, ultimo);
}





int main() {

    start();

    char line[256];
    char codigo[37];
    scanf("%s",codigo);   

    // Lê a linha de cabeçalho (ignorada neste exemplo)
    while (strcmp(codigo,"FIM")!=0)
    {
      FILE *file = fopen(FILE_PATH, "r");
    if (file == NULL) {
        perror("File not found exception.");
        return 1;
        fgets(line, sizeof(line), file);
    }  


    // Lê cada linha do arquivo e processa
    while (fgets(line, sizeof(line), file)) {

        // Mapeia a linha CSV para um objeto Character
        Character *character = csvLine_mapper(line);
        if(character && strcmp(codigo,character->id)==0){
        inserirFim(character);

    }
        }
    //le dnv
    scanf("%s", codigo);
    // Fecha o arquivo após a leitura
    fclose(file);
    }

    // Ordena o array de personagens pela house e nome usando quick Sort
    quickSort();
    mostrar();


    return 0;
}

// Função para liberar um array de strings
void free_string_array(char **array, int length) {
    if (array == NULL)
        return;

    // Libera cada string do array
    for (int i = 0; i < length; i++) {
        free(array[i]);
    }

    // Libera o próprio array de strings
    free(array);
}

// Função para liberar um objeto Character
void free_character(Character *character) {
    if (character == NULL)
        return;

    // Libera as strings do objeto Character
    free(character->id);
    free(character->name);
    free(character->house);
    free(character->ancestry);
    free(character->species);
    free(character->patronus);
    free(character->actorName);
    free(character->dateOfBirth);
    free(character->eyeColour);
    free(character->gender);
    free(character->hairColour);

    // Libera as listas de strings do objeto Character
    if (character->alternateNames)
        free_string_array(character->alternateNames, character->alternateNamesCount);
    if (character->alternateActors)
        free_string_array(character->alternateActors, character->alternateActorsCount);

    // Libera o próprio objeto Character
    free(character);
}
