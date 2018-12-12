package edoe;

import java.util.*;

/**
 * Classe responsavel pelo gerenciamento de itens, pelo padrao CRUD.
 *
 * @author Brener Quevedo, Iago Oliveira, Paulo Moreira
 */
public class ItemController {

	
	private List<String> doacoes;
    /**
     * Mapa de itens doados, em que a key é o identificador do usuário, e o valor é outro mapa, sendo este de itens, no qual a key é o identificador do item e o value é um objeto do tipo Item.
     */
    private Map<String, Map<String, Item>> itensDoacao;
    /**
     * Mapa de itens necessários, em que a key é o identificador do usuário, e o valor é outro mapa, sendo este de itens, no qual a key é o identificador do item e o value é um objeto do tipo Item.
     */
    private Map<String, Map<String, Item>> itensNecessarios;
    /**
     * Mapa de descritores, responsável por salvar as descrições dos itens, sendo a key uma string que representa a descrição e o value uma List de itens.
     */
    private Map<String, List<Item>> descritores;
    /**
     * atributo que representa o identificador do item, do tipo inteiro.
     */
    private int idItem;

    /**
     * Construtor do controller e instanciação dos atributos
     * obs: o map de itens necessários será um TreeMap pois os itens terão de ser listados de forma ordenada.
     */
    public ItemController () {
        this.doacoes = new ArrayList<>();
    	this.itensDoacao = new HashMap<>();
        this.itensNecessarios = new HashMap<>();
        this.descritores = new TreeMap<>();
        this.idItem = 0;
    }

    /**
     * Adiciona um novo descritor de item ao mapa de descritores.
     * @param descricao Descritor de item a ser adicionado.
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public void adicionaDescritor (String descricao) throws IllegalArgumentException, NullPointerException{
        if (descricao == null) {
            throw new NullPointerException("Entrada invalida: descricao nao pode ser vazia ou nula.");
        }
        descricao = descricao.trim().toLowerCase();
        if (descricao.equals("")) {
            throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
        }
        if (this.descritores.containsKey(descricao)) {
            throw new IllegalArgumentException("Descritor de Item ja existente: " + descricao + ".");
        }
        this.descritores.put(descricao, new ArrayList<>());
    }

    /**
     * Adiciona um item para doacao no mapa de Doadores -> itens para doacao.
     * @param idDoador Id do doador a ter um item adicionado.
     * @param descricaoItem Descricao do item a ser adicionado.
     * @param tags Tags do item a ser adicionado.
     * @param quantidade Quantidade do item a ser adicionado.
     * @param nomeDoador Nome do doador que tera um item adicionado.
     * @return Retorna o identificador do item.
     */
    public String adicionaItemParaDoacao (String idDoador, String descricaoItem, String tags, int quantidade, String nomeDoador) throws IllegalArgumentException, NullPointerException{

        if (this.itensDoacao.containsKey(idDoador)) {
            for (String id : this.itensDoacao.get(idDoador).keySet()) {
                if (this.itensDoacao.get(idDoador).get(id).getDescricao().equals(descricaoItem) && this.itensDoacao.get(idDoador).get(id).getTags().equals(formataTags(tags))) {
                    this.atualizaItemParaDoacao(id, idDoador, quantidade, tags);
                    return id;
                }
            }
        }

        this.idItem += 1;
        Item item = new Item(Integer.toString(idItem), descricaoItem, formataTags(tags), quantidade, nomeDoador, idDoador, "doacao");

        if (!this.itensDoacao.containsKey(idDoador)) {
            this.itensDoacao.put(idDoador, new HashMap<>());
        }
        this.itensDoacao.get(idDoador).put(Integer.toString(idItem), item);

        if (!this.descritores.containsKey(descricaoItem)) {
            this.descritores.put(descricaoItem, new ArrayList<>());
        }

        this.descritores.get(descricaoItem).add(item);

        return Integer.toString(this.idItem);
    }

    /**
     * Exibe as caracteristicas gerais de um item.
     * @param idUsuario Id do usuario a ter um item retornado.
     * @param idItem Id do item a ser retornador.
     * @return Retorna o toString() do item.
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public String exibeItem(String idUsuario, String idItem) throws IllegalArgumentException, NullPointerException {
        if (!this.itensDoacao.containsKey(idUsuario)) {
            throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + ".");
        }
        if (!this.itensDoacao.get(idUsuario).containsKey(idItem)) {
            throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
        }
        return this.itensDoacao.get(idUsuario).get(idItem).toString();
    }

    /**
     * Atualiza a quantidade de itens a serem doados OU suas tags.
     * @param idItem Id do item a ser atualziado.
     * @param idDoador Id do doador a ter o item atualizado.
     * @param novaQuantidade Nova quantidade do item. Caso a quantidade seja 0, ela nao sera editada.
     * @param novasTags Novas tags do item. Caso as tags sejam vazias, elas nao seram alteradas.
     * @return Retorna o toString() do item ja editado.
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public String atualizaItemParaDoacao (String idItem, String idDoador, int novaQuantidade, String novasTags) throws IllegalArgumentException, NullPointerException {
        if (Integer.parseInt(idItem) < 0) {
            throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
        }
        if (idDoador == null) {
            throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (idDoador.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (!this.itensDoacao.containsKey(idDoador)) {
            throw new NullPointerException("Usuario nao encontrado: " + idDoador+ ".");
        }
        if (!this.itensDoacao.get(idDoador).containsKey(idItem)) {
            throw new NullPointerException("Item nao encontrado: " + idItem + ".");
        }
        if (novasTags != null) {
            novasTags = formataTags(novasTags);
            this.itensDoacao.get(idDoador).get(idItem).setTags(novasTags);
        }
        if (novaQuantidade > 0) {
            this.itensDoacao.get(idDoador).get(idItem).setQuantidade(novaQuantidade);
        }
        return this.itensDoacao.get(idDoador).get(idItem).toString();
    }

    private int getQuantidadeDeItensComDescritor (String descritor) {
        int quantidade = 0;
        for (Item i : this.descritores.get(descritor)) {
            quantidade += i.getQuantidade();
        }
        return quantidade;
    }

    /**
     * Lista todos os descritores que antes foram adicionados pelo metodo adicionaDescritor ou pelo cadastro de um item cujo descricao nao existia..
     * @return Retorna todos os descritores ordenados por ordem alfabetica.
     */
    public String listaDescritorDeItensParaDoacao () {
        List<String> decritores = new ArrayList<>();
        for (String descritor : this.descritores.keySet()) {
            decritores.add(getQuantidadeDeItensComDescritor(descritor) + " - " + descritor);
        }
        return constroiListagem(decritores);
    }

    /**
     * Lista todos os itens para doacao, ordenados por quantidade e por ordem alfabética.
     * @return String com as caracteristicas gerais de todos os itens e com os seus respectivos doadores.
     */
    public String listaItensParaDoacao () {
        List<Item> arrayDeItens = new ArrayList<>();
        for (String idDoador : this.itensDoacao.keySet()) {
            arrayDeItens.addAll(this.itensDoacao.get(idDoador).values());
        }

        arrayDeItens.sort(new ComparaItemPorQuantidade());

        List<String> arrayDeListagem = new ArrayList<>();
        for (Item item : arrayDeItens) {
            arrayDeListagem.add(item.toString() + ", doador: " + item.getNomeUsuario() + "/" + item.getIdUsuario());
        }

        return constroiListagem(arrayDeListagem);
    }

    /**
     * Retorna todos os itens com uma certa descricao.
     * @param descricao Descricao a ser buscada nos itens.
     * @return Retorna o toString() de todos os itens cadastrados que tenham a descricao semelhante , sendo ordenados em ordem alfabética.
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public String pesquisaItemParaDoacaoPorDescricao (String descricao) throws IllegalArgumentException, NullPointerException {
        if (descricao == null) {
            throw new NullPointerException("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
        }
        if (descricao.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
        }

        List<Item> listaDeItensComDescricao = new ArrayList<>();

        for (String desc : this.descritores.keySet()) {
            if (desc.contains(descricao)) {
                listaDeItensComDescricao.addAll(this.descritores.get(desc));
            }
        }

        Collections.sort(listaDeItensComDescricao);

        List<String> listaDeStringsDosItensComDescricao = new ArrayList<>();
        for (Item item : listaDeItensComDescricao) {
            listaDeStringsDosItensComDescricao.add(item.toString());
        }

        return constroiListagem(listaDeStringsDosItensComDescricao);
    }

    /**
     * Remove um item do mapa de itensDoacao (um item adicionado para doacao).
     * @param idItem Id do item a ser removido.
     * @param idUsuario Id do usuario a ter um item removido.
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public void removeItemParaDoacao(String idItem, String idUsuario) throws IllegalArgumentException, NullPointerException {
        if (idUsuario == null) {
            throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (idUsuario.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }

        if (!this.itensDoacao.containsKey(idUsuario)) {
            throw new NullPointerException("O Usuario nao possui itens cadastrados.");
        }
        if (Integer.parseInt(idItem) < 0) {
            throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
        }
        if (!this.itensDoacao.get(idUsuario).containsKey(idItem)) {
            throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
        }
        this.descritores.get(this.itensDoacao.get(idUsuario).get(idItem).getDescricao()).remove(this.itensDoacao.get(idUsuario).get(idItem));
        this.itensDoacao.get(idUsuario).remove(idItem);

    }

    /**
     * Adiciona um item necessário no mapa de Receptores -> itens necessarios..
     * @param idReceptor Id do receptor a ter um item adicionado.
     * @param descricaoItem Descricao do item a ser adicionado.
     * @param tags Tags do item a ser adicionado.
     * @param quantidade Quantidade necessaria do item a ser adicionado.
     * @param nomeReceptor Nome do receptor que tera um item adicionado.
     * @return Retorna o itentificador do item.
     */
    public String adicionaItemNecessario (String idReceptor, String descricaoItem, String tags, int quantidade, String nomeReceptor) {
        if (descricaoItem == null) {
            throw new NullPointerException("Entrada invalida: descricao nao pode ser vazia ou nula.");
        }
        descricaoItem = descricaoItem.trim().toLowerCase();

        if (descricaoItem.equals("")) {
            throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
        }
        if (idReceptor == null) {
            throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (idReceptor.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }

        if (this.itensNecessarios.containsKey(idReceptor)) {
            for (String id : this.itensNecessarios.get(idReceptor).keySet()) {
                if (this.itensNecessarios.get(idReceptor).get(id).getDescricao().equals(descricaoItem) && this.itensNecessarios.get(idReceptor).get(id).getTags().equals(formataTags(tags))) {
                    this.atualizaItemNecessario(id, idReceptor, quantidade, tags);
                    return id;
                }
            }
        }

        this.idItem += 1;
        Item item = new Item(Integer.toString(idItem), descricaoItem, formataTags(tags), quantidade, nomeReceptor, idReceptor, "necessario");

        if (!this.itensNecessarios.containsKey(idReceptor)) {
            this.itensNecessarios.put(idReceptor, new HashMap<>());
        }
        this.itensNecessarios.get(idReceptor).put(Integer.toString(idItem), item);

        if (!this.descritores.containsKey(descricaoItem)) {
            this.descritores.put(descricaoItem, new ArrayList<>());
        }
        this.descritores.get(descricaoItem).add(item);

        return Integer.toString(this.idItem);
    }

    /**
     * Retorna todos os itens necessarios cadastrados, ordenados por id.
     * @return Retorna o toString de todos os itens adicionados.
     */
    public String listaItensNecessarios () {
        StringBuilder builder = new StringBuilder();
        Map<Integer, String> tree = new TreeMap<>();

        for (String idReceptor : this.itensNecessarios.keySet()) {
            for (String idItem : this.itensNecessarios.get(idReceptor).keySet()) {
                tree.put(Integer.parseInt(idItem), this.itensNecessarios.get(idReceptor).get(idItem).toString() +
                        ", Receptor: " + this.itensNecessarios.get(idReceptor).get(idItem).getNomeUsuario() +
                        "/" + idReceptor);
            }
        }

        boolean v = false;
        for (Integer i : tree.keySet()) {
            if (v) {
                builder.append(" | ");
            }
            builder.append(tree.get(i));
            v = true;
        }

        return builder.toString();
    }

    /**
     * Atualiza a quantidade de itens a serem doados OU suas tags.
     * @param idReceptor Id do receptor a ter um item necessario alterado.
     * @param idItem Id do item necessario a ser alterado.
     * @param novaQuantidade Nova quantidade necessaria do item. Caso a nova quantidade seja 0, ela nao sera alterada.
     * @param novasTags Novas tags do item necessario. Caso as tags sejam uma String vazia, elas nao seram alteradas.
     * @return toString do item modificado
     * @throws NullPointerException
     */
    public String atualizaItemNecessario (String idItem, String idReceptor, int novaQuantidade, String novasTags) throws NullPointerException {
        if (Integer.parseInt(idItem) < 0) {
            throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
        }
        if (idReceptor == null) {
            throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (idReceptor.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }

        if (!this.itensNecessarios.containsKey(idReceptor)) {
            throw new NullPointerException("O Usuario nao possui itens cadastrados.");
        }
        if (!this.itensNecessarios.get(idReceptor).containsKey(idItem)) {
            throw new NullPointerException("Item nao encontrado: " + idItem + ".");
        }

        if (novasTags != null && !novasTags.equals("")) {
            novasTags = formataTags(novasTags);
            this.itensNecessarios.get(idReceptor).get(idItem).setTags(novasTags);
        }
        if (novaQuantidade > 0) {
            this.itensNecessarios.get(idReceptor).get(idItem).setQuantidade(novaQuantidade);
        }
        return this.itensNecessarios.get(idReceptor).get(idItem).toString();
    }

    /**
     * Remove um item do mapa de itensNecessarios (um item adicionado para ser recebido)
     * @param idReceptor Id do receptor a ter um item necessario removido.
     * @param idItem Id no item necessario a ser removido.
     * @throws NullPointerException
     */
    public void removeItemNecessario (String idReceptor, String idItem) throws NullPointerException {
        if (Integer.parseInt(idItem) < 0) {
            throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
        }
        if (idReceptor == null) {
            throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (idReceptor.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }

        if (!this.itensNecessarios.containsKey(idReceptor)) {
            throw new NullPointerException("O Usuario nao possui itens cadastrados.");
        }
        if (!this.itensNecessarios.get(idReceptor).containsKey(idItem)) {
            throw new NullPointerException("Item nao encontrado: " + idItem + ".");
        }

        this.itensNecessarios.get(idReceptor).remove(idItem);
    }

    private String formataTags(String tags) {
        return "[" + tags.replace(",", ", ") + "]";
    }

    private String constroiListagem (List<String> lista) {
        StringBuilder builder = new StringBuilder();
        boolean adicionaSeparador = false;
        for (String s : lista) {
            if (adicionaSeparador) {
                builder.append(" | ");
            }
            builder.append(s);
            adicionaSeparador = true;
        }
        return builder.toString();
    }

    public String match (String idReceptor, String idItemNecessario) {
        if (idReceptor == null) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (idReceptor.equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (Integer.parseInt(idItemNecessario) < 0) {
            throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
        }


        if (!this.itensNecessarios.get(idReceptor).containsKey(idItemNecessario)) {
            throw new NullPointerException("Item nao encontrado: " + idItemNecessario + ".");
        }


        Item itemNecessario = this.itensNecessarios.get(idReceptor).get(idItemNecessario);
        Map<Integer, List<String>> tree = new TreeMap<>(Collections.reverseOrder());

        for (Item item : this.descritores.get(itemNecessario.getDescricao().toLowerCase())) {
            if (item.getCategoria().equals("doacao")) {
                int pontos = calculaPontos(itemNecessario, item);

                if (!tree.containsKey(pontos)) {
                    tree.put(pontos, new ArrayList<>());
                }

                tree.get(pontos).add(item.toStringComDoador());
            }
        }

        if (tree.size() == 0) {
            throw new IllegalArgumentException("Item nao tem nenhum match.");
        }

        List<String> matches = new ArrayList<>();

        for (int pontos : tree.keySet()) {
            Collections.sort(tree.get(pontos));
            matches.addAll(tree.get(pontos));
        }
        return constroiListagem(matches);
    }

    private int calculaPontos (Item itemNecessario, Item itemParaDoacao) {
        int pontos = 0;

        List<String> tagsDoItemNecessario = Arrays.asList(itemNecessario.getTags().replace("[", "").replace("]", "").split(", "));
        List<String> tagsDoItemParaDoacao = Arrays.asList(itemParaDoacao.getTags().replace("[", "").replace("]", "").split(", "));
        for (String tag : tagsDoItemNecessario) {
            if (tagsDoItemParaDoacao.contains(tag)) {
                if (tagsDoItemParaDoacao.indexOf(tag) == tagsDoItemNecessario.indexOf(tag)) {
                    pontos += 10;
                } else {
                    pontos += 5;
                }
            }
        }
        return pontos;
    }
   
    /**
     * Realiza a doacao de um itemParaDoacao para um receptor que tem um itemNecessario com mesma descricao.
     * @param idItemNecessario id do item necessario.
     * @param idItemParaDoacao id do item para doacao.
     * @param data data em que esta sendo realizada a doacao.
     * @return retorna uma representacao da doacao com data, dados do doador e receptor, descricao do produto doado e quantidade doada do produto.
     * @throws ItemInexistenteException excecao lancada quando o item nao existe ou quando as descricoes dos itens nao sao iguais.
     * @throws NullPointerException quando alguma entrada do metodo for nula.
     * @throws IllegalArgumentException quando alguma entrada for vazia ou menor que 0.
     */
    public String realizaDoacao(String idItemNecessario, String idItemParaDoacao, String data) throws ItemInexistenteException, NullPointerException, IllegalArgumentException   {
    	if(data == null) {
    		throw new NullPointerException("Entrada invalida: data nao pode ser vazia ou nula.");
    	}
    	if(idItemNecessario == null) {
    		throw new NullPointerException("id item necessario nao pode ser nulo");
    	}
    	if(idItemParaDoacao == null) {
    		throw new NullPointerException("id item para doacao nao pode ser nulo;");
    	}
    	if(Integer.parseInt(idItemParaDoacao) < 0 || Integer.parseInt(idItemNecessario) < 0) {
    		throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
    	}
    	if(data.trim().equals("")) {
    		throw new IllegalArgumentException("Entrada invalida: data nao pode ser vazia ou nula.");
    	}
    	
    	Item itemNecessario = null;
    	Item itemDoacao = null;
    	for(String idReceptor : this.itensNecessarios.keySet()) {
    		if(this.itensNecessarios.get(idReceptor).containsKey(idItemNecessario)) {
    			itemNecessario = this.itensNecessarios.get(idReceptor).get(idItemNecessario);
    		}
    	}
    	for(String idDoador : this.itensDoacao.keySet()) {
    		if(this.itensDoacao.get(idDoador).containsKey(idItemParaDoacao)) {
    			itemDoacao = this.itensDoacao.get(idDoador).get(idItemParaDoacao);
    		}
    	}
    	if(itemDoacao == null) {
    		throw new ItemInexistenteException("Item nao encontrado: " + idItemParaDoacao + ".");
    	}
    	if(itemNecessario == null) {
    		throw new ItemInexistenteException("Item nao encontrado: " + idItemNecessario + ".");
    	}
    	if(itemNecessario.getDescricao().toLowerCase().contains(itemDoacao.getDescricao().toLowerCase())) {
    		int itensDoados =  this.doacao(itemNecessario, itemDoacao);
    		String doacao = String.format("%s - doador: %s/%s, item: %s, quantidade: %d, receptor: %s/%s", data, itemDoacao.getNomeUsuario(), itemDoacao.getIdUsuario(), itemDoacao.getDescricao(),itensDoados, itemNecessario.getNomeUsuario(), itemNecessario.getIdUsuario());
    		this.doacoes.add(doacao);
    		return doacao;
    	}else {
    		throw new ItemInexistenteException("Os itens nao tem descricoes iguais.");
    	}
    }

	private int doacao(Item itemNecessario, Item itemDoacao) {
		if(itemNecessario.getQuantidade() > itemDoacao.getQuantidade()) {
			itemNecessario.setQuantidade(itemNecessario.getQuantidade() - itemDoacao.getQuantidade());
			this.removeItemParaDoacao(itemDoacao.getIdItem(), itemDoacao.getIdUsuario());
			return itemDoacao.getQuantidade();
		}
		else if( itemDoacao.getQuantidade() > itemNecessario.getQuantidade() ) {
			itemDoacao.setQuantidade(itemDoacao.getQuantidade() - itemNecessario.getQuantidade() );
			this.removeItemNecessario(itemNecessario.getIdUsuario(), itemNecessario.getIdItem());
			return itemNecessario.getQuantidade();
		}
		else {
			this.removeItemParaDoacao(itemDoacao.getIdItem(), itemDoacao.getIdUsuario());
			this.removeItemNecessario(itemNecessario.getIdUsuario(), itemNecessario.getIdItem());
			return itemNecessario.getQuantidade();
		}
	}

	/**
	 * Metodo que lista todas as doacoes ordenadas por sua data. Caso a data seja igual, ela se ordena pelo descritor do item.
	 * @return retorna a representacao de cada doacao separado por " | " .
	 * @throws Exception lanca uma excecao checada no cast da data. Uma ParseException.
	 */
	public String listaDoacoes() throws Exception {
		this.doacoes.sort(new comparaItemPorData());
		StringBuilder builder = new StringBuilder();
		boolean adicionaSeparador = false;
		for(String doacao : this.doacoes) {
			if(adicionaSeparador) {
				builder.append(" | ");
			}
			builder.append(doacao);
			adicionaSeparador = true;
		}
		return builder.toString();
	}
}

