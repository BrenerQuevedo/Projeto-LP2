package edoe;

import javax.swing.tree.TreeCellRenderer;
import java.util.*;

/**
 * Classe responsavel pelo gerenciamento de itens, pelo padrao CRUD.
 *
 * @author Brener Quevedo, Iago Oliveira
 */

public class ItemController {

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
        Item item = new Item(Integer.toString(idItem), descricaoItem, formataTags(tags), quantidade, nomeDoador);

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

    /**
     * Lista todos os descritores que antes foram adicionados pelo metodo adicionaDescritor ou pelo cadastro de um item cujo descricao nao existia..
     * @return Retorna todos os descritores ordenados por ordem alfabetica.
     */
    public String listaDescritorDeItensParaDoacao () {
        StringBuilder builder = new StringBuilder();
        Map<String, Integer> tree = new TreeMap<>();
        int quantidade;
        boolean v = false;
        for (String s : this.descritores.keySet()) {
            if (v) {
                builder.append(" | ");
            }
            quantidade = 0;
            for (Item i : this.descritores.get(s)) {
                quantidade += i.getQuantidade();
            }
            builder.append(quantidade).append(" - ").append(s);
            v = true;
        }

        return builder.toString();
    }

    /**
     * Lista todos os itens para doacao, ordenados por quantidade e por ordem alfabética.
     * @return String com as caracteristicas gerais de todos os itens e com os seus respectivos doadores.
     */
    public String listaItensParaDoacao () {
        Map<Integer, Map<String, String>> tree = new TreeMap<>(Collections.reverseOrder());

        for (String idUsuario : itensDoacao.keySet()) {
            for (String idItem : itensDoacao.get(idUsuario).keySet()) {
                if (!tree.containsKey(this.itensDoacao.get(idUsuario).get(idItem).getQuantidade())) {
                    tree.put(this.itensDoacao.get(idUsuario).get(idItem).getQuantidade(), new TreeMap<>());
                }
                tree.get(this.itensDoacao.get(idUsuario).get(idItem).getQuantidade()).put(this.itensDoacao.get(idUsuario).get(idItem).getDescricao(),
                        itensDoacao.get(idUsuario).get(idItem).toString() + ", doador: " + itensDoacao.get(idUsuario).get(idItem).getNomeUsuario()
                        + "/" + idUsuario);
            }
        }

        StringBuilder builder = new StringBuilder();
        boolean v = false;
        for (Integer i : tree.keySet()) {
            for (String s : tree.get(i).keySet()) {
                if (v) {
                    builder.append(" | ");
                }
                builder.append(tree.get(i).get(s));
                v = true;
            }
        }

        return builder.toString();
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
        StringBuilder builder = new StringBuilder();
        List<String> lista = new ArrayList<>();

        for (String s : this.descritores.keySet()) {
            if (s.toLowerCase().contains(descricao.toLowerCase())) {
                lista.add(s);
            }
        }

        Collections.sort(lista);

        boolean v = false;
        for (String s : lista) {
            if (v) {
                builder.append(" | ");
            }
            builder.append(itensComDescritor(s));
            v = true;
        }
        return builder.toString();
    }

    /**
     * Remove um item do mapa de itensDoacao (um item adicionado para doacao).
     * @param idItem Id do item a ser removido.
     * @param idUsuario Id do usuario a ter um item removido.
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public void removeItemParaDoacao(String idItem, String idUsuario) throws IllegalArgumentException, NullPointerException {
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
        if (this.itensNecessarios.containsKey(idReceptor)) {
            for (String id : this.itensNecessarios.get(idReceptor).keySet()) {
                if (this.itensNecessarios.get(idReceptor).get(id).getDescricao().equals(descricaoItem) && this.itensNecessarios.get(idReceptor).get(id).getTags().equals(formataTags(tags))) {
                    this.atualizaItemNecessario(id, idReceptor, quantidade, tags);
                    return id;
                }
            }
        }

        this.idItem += 1;
        Item item = new Item(Integer.toString(idItem), descricaoItem, formataTags(tags), quantidade, nomeReceptor);

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

    private String itensComDescritor (String s) {
        StringBuilder builder = new StringBuilder();
        List<Item> lista = new ArrayList<>(descritores.get(s));
        Collections.sort(lista);

        boolean v = false;
        for (Item i : lista) {
            if (v) {
                builder.append(" | ");
            }
            builder.append(i.toString());
            v = true;
        }
        return builder.toString();
    }
}