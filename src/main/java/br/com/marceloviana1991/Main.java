package br.com.marceloviana1991;

import br.com.marceloviana1991.convert.SerializadorJson;
import br.com.marceloviana1991.convert.TransformadorDto;
import br.com.marceloviana1991.model.Produto;
import br.com.marceloviana1991.model.ProdutoDTO;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Produto produto = new Produto(1, "produto", 5.5);
        TransformadorDto transformador = new TransformadorDto();
        SerializadorJson serializadorJson = new SerializadorJson();

        ProdutoDTO produtoDTO = transformador.transformar(produto, ProdutoDTO.class);

        String json = serializadorJson.transform(produtoDTO);

        System.out.println(json);
    }
}