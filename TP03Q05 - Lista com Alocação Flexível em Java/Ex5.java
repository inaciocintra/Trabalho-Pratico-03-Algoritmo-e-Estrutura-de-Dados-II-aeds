

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Personagem {

    // -----atributos personagem-----
    private String id;
    private String name;
    private ArrayList<String> alternate_names;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private Boolean hogwartsStaff;
    private Boolean hogwartsStudent;
    private String actorName;
    private Boolean alive;
    private Date dateOfBirth;
    private int yearOfBirth;
    private String eyeColour;
    private String gender;
    private String hairColour;
    private Boolean wizard;
    private SimpleDateFormat formatodedata = new SimpleDateFormat("dd-MM-yyyy");

    // -----contrutor sem parametros-----
    public Personagem() {
        this.id = null;
        this.name = null;
        this.alternate_names = new ArrayList<String>();
        this.house = null;
        this.ancestry = null;
        this.species = null;
        this.patronus = null;
        this.hogwartsStaff = null;
        this.hogwartsStudent = null;
        this.actorName = null;
        this.alive = null;
        this.dateOfBirth = null;
        this.yearOfBirth = 0;
        this.eyeColour = null;
        this.gender = null;
        this.hairColour = null;
        this.wizard = null;

    }

    // -----construtor-----
    public Personagem(String id, String name, ArrayList<String> alternate_names, String house, String ancestry,
            String species, String patronus, Boolean hogwartsStaff, Boolean hogwartsStudent, String actorName,
            Boolean alive, String dateOfBirth, int yearOfBirth, String eyeColour, String gender, String hairColour,
            Boolean wizard) {
        this.id = id;
        this.name = name;
        this.alternate_names = alternate_names;
        this.house = house;
        this.ancestry = ancestry;
        this.species = species;
        this.patronus = patronus;
        this.hogwartsStaff = hogwartsStaff;
        this.hogwartsStudent = hogwartsStudent;
        this.actorName = actorName;
        this.alive = alive;
        setDate(dateOfBirth);
        this.yearOfBirth = yearOfBirth;
        this.eyeColour = eyeColour;
        this.gender = gender;
        this.hairColour = hairColour;
        this.wizard = wizard;
    }

    // -----métodos gets e sets-----

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getList() {

        return alternate_names;
    }

    public void setList(String todosapelidos) {
        this.alternate_names.add(todosapelidos);
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getAncestry() {
        return ancestry;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getPatronus() {
        return patronus;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public Boolean getHogwartsStaff() {
        return hogwartsStaff;
    }

    public void setHogwartsStaff(String condicaostaff) {
        if (condicaostaff.equals("VERDADEIRO")) {

            this.hogwartsStaff = true;
        } else {
            this.hogwartsStaff = false;
        }
    }

    public Boolean getHogwartsStudent() {
        return hogwartsStudent;
    }

    public void setHogwartsStudent(String hogwartsStudent) {
        if (hogwartsStudent.equals("VERDADEIRO")) {

            this.hogwartsStudent = true;
        } else {
            this.hogwartsStudent = false;
        }
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(String vivo) {
        if (vivo.equals("VERDADEIRO")) {

            this.alive = true;
        } else {
            this.alive = false;
        }

    }

    public Date getDate() {
        return dateOfBirth;
    }

    public void setDate(String dataoriginal) {

        try {
            this.dateOfBirth = formatodedata.parse(dataoriginal);
        } catch (ParseException e) {
            System.out.println("Erro ao converter a data: " + e.getMessage());
        }
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int anoConvertido) {
        this.yearOfBirth = anoConvertido;

    }

    public String getEyeColour() {
        return eyeColour;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHairColour() {
        return hairColour;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public Boolean getWizard() {
        return wizard;
    }

    public void setWizard(String mago) {
        if (mago.equals("VERDADEIRO")) {

            this.wizard = true;
        } else {
            this.wizard = false;
        }

    }

    // -----método clone-----
    public Personagem clone() {
        Personagem temp = new Personagem();
        temp.setYearOfBirth(this.yearOfBirth);
        temp.setName(this.name);
        return temp;
    }

    // -----método imprimir-----
    public void imprime() {
        System.out.print(  getId() + " ## " + getName() + " ## ");
        System.out.print("{");
        for (int i = 0; i < alternate_names.size(); i++) {
            System.out.print(alternate_names.get(i));
            if (i < alternate_names.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("} ");
        System.out.println("## " + getHouse() + " ## " + getAncestry() + " ## " + getSpecies() + " ## " + getPatronus()
                + " ## " + getHogwartsStaff() + " ## " + getHogwartsStudent() + " ## " + getActorName() + " ## "
                + getAlive() + " ## " + formatodedata.format(getDate()) + " ## " + getYearOfBirth() + " ## "
                + getEyeColour() + " ## " + getGender() + " ## " + getHairColour() + " ## " + getWizard() + "]");

    }

    // -----método ler-----
    // recebe linha que vem da main
    public void ler(String linha) {
        // divide a linha ate cada ponto e virgula
        String[] dados = linha.split(";");
        if (dados.length >= 17) {
            setId(dados[0]);
            setName(dados[1]);
            // pega string dos apelidos e retira as chaves aspas e deixa somente os apelidos
            // e a virgula
            String regex = "\\s+'|\\[|\\]|\\'";
            dados[2] = dados[2].replaceAll(regex, "");
            String[] temp = dados[2].split(",");
            for (int i = 0; i < temp.length; i++) {
                setList(temp[i]);
            }
            setHouse(dados[3]);
            setAncestry(dados[4]);
            setSpecies(dados[5]);
            setPatronus(dados[6]);
            setHogwartsStaff(dados[7]);// alterar
            setHogwartsStudent(dados[8]);
            setActorName(dados[9]);
            setAlive(dados[10]);// alterar
            setDate(dados[12]);// alterar

            // alterando o ano de nascimento de string para int e chamando o set
            try {
                int anoConvertido = Integer.parseInt(dados[13]);

                setYearOfBirth(anoConvertido);// set como o ano ja em inteiro

            } catch (NumberFormatException e) {
                System.out.println("Erro ao converter a string para inteiro: " + e.getMessage());
            }

            setEyeColour(dados[14]);
            setGender(dados[15]);
            setHairColour(dados[16]);
            setWizard(dados[17]);// alterar
        }

    }
}

class Celula {
	public Personagem personagem; // Elemento inserido na celula.
	public Celula prox; // Aponta a celula prox.


	
	public Celula() {
		this(null);
	}

	/**
	 * Construtor da classe.
	 * @param personagem int inserido na celula.
	 */
	public Celula(Personagem personagem) {
      this.personagem = personagem;
      this.prox = null;
	}
}

class Lista {
	private Celula primeiro;
	private Celula ultimo;


	/**
	 * Construtor da classe que cria uma lista sem personagem (somente no cabeca).
	 */
	public Lista() {
		primeiro = new Celula();
		ultimo = primeiro;
	}


	/**
	 * Insere um elemento na primeira posicao da lista.
    * @param x int elemento a ser inserido.
	 */
	public void inserirInicio(Personagem personagem) {
		Celula tmp = new Celula(personagem);
      tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if (primeiro == ultimo) {                 
			ultimo = tmp;
		}
      tmp = null;
	}


	/**
	 * Insere um elemento na ultima posicao da lista.
    * @param x int elemento a ser inserido.
	 */
	public void inserirFim(Personagem x) {
		ultimo.prox = new Celula(x);
		ultimo = ultimo.prox;
	}


	/**
	 * Remove um elemento da primeira posicao da lista.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Personagem removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

      Celula tmp = primeiro;
		primeiro = primeiro.prox;
		Personagem resp = primeiro.personagem;
      tmp.prox = null;
      tmp = null;
		return resp;
	}


	/**
	 * Remove um elemento da ultima posicao da lista.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Personagem removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} 

		// Caminhar ate a penultima celula:
      Celula i;
      for(i = primeiro; i.prox != ultimo; i = i.prox);

      Personagem resp = ultimo.personagem; 
      ultimo = i; 
      i = ultimo.prox = null;
      
		return resp;
	}


	/**
    * Insere um elemento em uma posicao especifica considerando que o 
    * primeiro elemento valido esta na posicao 0.
    * @param x int elemento a ser inserido.
	 * @param pos int posicao da insercao.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
   public void inserir(Personagem personagem, int pos) throws Exception {

      int tamanho = tamanho();

      if(pos < 0 || pos > tamanho){
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
      } else if (pos == 0){
         inserirInicio(personagem);
      } else if (pos == tamanho){
         inserirFim(personagem);
      } else {
		   // Caminhar ate a posicao anterior a insercao
         Celula i = primeiro;
         for(int j = 0; j < pos; j++, i = i.prox);
		
         Celula tmp = new Celula(personagem);
         tmp.prox = i.prox;
         i.prox = tmp;
         tmp = i = null;
      }
   }


	/**
    * Remove um elemento de uma posicao especifica da lista
    * considerando que o primeiro elemento valido esta na posicao 0.
	 * @param posicao Meio da remocao.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public Personagem remover(int pos) throws Exception {
      Personagem resp;
      int tamanho = tamanho();

		if (primeiro == ultimo){
			throw new Exception("Erro ao remover (vazia)!");

      } else if(pos < 0 || pos >= tamanho){
			throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
      } else if (pos == 0){
         resp = removerInicio();
      } else if (pos == tamanho - 1){
         resp = removerFim();
      } else {
		   // Caminhar ate a posicao anterior a insercao
         Celula i = primeiro;
         for(int j = 0; j < pos; j++, i = i.prox);
		
         Celula tmp = i.prox;
         resp = tmp.personagem;
         i.prox = tmp.prox;
         tmp.prox = null;
         i = tmp = null;
      }

		return resp;
	}

	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	

    public void imprimirlista() {
        int j = 0;
        for (Celula i = primeiro.prox; i != null; j++, i = i.prox) {
            
			System.out.print("[" + j + " ## ");
             i.personagem.imprime();
		}
        
          
    }

	/**
	 * Procura um elemento e retorna se ele existe.
	 * @param x Elemento a pesquisar.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(Personagem x) {
		boolean resp = false;
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
         if(i.personagem == x){
            resp = true;
            i = ultimo;
         }
		}
		return resp;
	}

	/**
	 * Calcula e retorna o tamanho, em numero de elementos, da lista.
	 * @return resp int tamanho
	 */
   public int tamanho() {
      int tamanho = 0; 
      for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
      return tamanho;
   }
}

class Ex5 {
    

    public static void main(String[] args) {
        Personagem[] personagens = new Personagem[500];
        Lista listapersonagens = new Lista();
        int j = 0;
        FileReader filereader;
        BufferedReader reader;
        Scanner entrada = new Scanner(System.in);
        String codigo;
        String nomeArquivo = "/tmp/characters.csv";
        int numoperacoes;
        int k = 0;
        String comandos;
        int pos;

        try {

            codigo = entrada.nextLine();
            String linha;
            Boolean existe = true;

            // le a primeira linha csv e nao usa ela

            // enquanto o codigo da pub.in diferente de fim
            while (!(codigo.equals("FIM"))) {
                filereader = new FileReader(nomeArquivo);
                reader = new BufferedReader(filereader);
                reader.readLine();
                // pega a linha csv
                while ((linha = reader.readLine()) != null) {

                    // compara com a linha do csv
                    for (int i = 0; i < 32; i++) {
                        if (linha.charAt(i) != codigo.charAt(i)) {
                            existe = false;
                            i = 32;
                        } else {
                            existe = true;
                        }

                    }

                    // se o codigo/id existir no csv
                    if (existe) {
                        // cria um personagem e chama o metodo ler da classe personagem passando a linha
                        // por parametro que irá setar os atributos
                        Personagem personagem = new Personagem();
                        personagem.ler(linha);
                        try {
                            listapersonagens.inserirFim(personagem);
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                        j++;

                    }

                }

                codigo = entrada.nextLine();
                filereader.close();
                reader.close();
            }
            numoperacoes = entrada.nextInt();

            while (k < numoperacoes) {
                comandos = entrada.next().trim();
                
                filereader = new FileReader(nomeArquivo);
                reader = new BufferedReader(filereader);
                reader.readLine();



                if (comandos.equals("II")) {
                    codigo = entrada.nextLine().trim();
            

                    // pega a linha csv
                    while ((linha = reader.readLine()) != null) {

                        // compara com a linha do csv
                        for (int i = 0; i < 32; i++) {
                            if (linha.charAt(i) != codigo.charAt(i)) {
                                existe = false;
                                i = 32;
                            } else {
                                existe = true;
                            }

                        }

                        // se o codigo/id existir no csv
                        if (existe) {
                            // cria um personagem e chama o metodo ler da classe personagem passando a linha
                            // por parametro que irá setar os atributos
                            Personagem personagem = new Personagem();
                            personagem.ler(linha);
                            try {
                                listapersonagens.inserirInicio(personagem);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }




                else if (comandos.equals("IF")) {
                    codigo = entrada.nextLine().trim();
                    

                    // pega a linha csv
                    while ((linha = reader.readLine()) != null) {

                        // compara com a linha do csv
                        for (int i = 0; i < 32; i++) {
                            if (linha.charAt(i) != codigo.charAt(i)) {
                                existe = false;
                                i = 32;
                            } else {
                                existe = true;
                            }

                        }

                        // se o codigo/id existir no csv
                        if (existe) {
                            // cria um personagem e chama o metodo ler da classe personagem passando a linha
                            // por parametro que irá setar os atributos
                            Personagem personagem = new Personagem();
                            personagem.ler(linha);
                            try {
                                listapersonagens.inserirFim(personagem);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }





                else if (comandos.equals("R*")) {
                    pos = entrada.nextInt();
                    try {
                        System.out.println("(R) " +listapersonagens.remover(pos).getName() );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } 




                
                
                else if (comandos.equals("RI")) {
                    try {
                        
                        System.out.println("(R) " +listapersonagens.removerInicio().getName() );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } 
                
                
                
                
                else if (comandos.equals("RF")) {
                    try {

                        System.out.println("(R) "+listapersonagens.removerFim().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } 
                
                
                
                
                else if (comandos.equals("I*")) {
                    pos = entrada.nextInt();
                    codigo = entrada.nextLine().trim();
                    

                    // pega a linha csv
                    while ((linha = reader.readLine()) != null) {

                        // compara com a linha do csv
                        for (int i = 0; i < 32; i++) {
                            if (linha.charAt(i) != codigo.charAt(i)) {
                                existe = false;
                                i = 32;
                            } else {
                                existe = true;
                            }

                        }

                        // se o codigo/id existir no csv
                        if (existe) {
                            // cria um personagem e chama o metodo ler da classe personagem passando a linha
                            // por parametro que irá setar os atributos
                            Personagem personagem = new Personagem();
                            personagem.ler(linha);
                            try {
                                listapersonagens.inserir(personagem, pos);
                            } catch (Exception e) {

                                e.printStackTrace();
                            }
                        }
                    }
                }



                filereader.close();
                reader.close();
                k++;
            }
            listapersonagens.imprimirlista();

            
        } catch (IOException e) {
            e.printStackTrace();

        }

        entrada.close();
    }

}