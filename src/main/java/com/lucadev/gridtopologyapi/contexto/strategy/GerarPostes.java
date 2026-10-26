package com.lucadev.gridtopologyapi.contexto.strategy;

import com.lucadev.gridtopologyapi.contexto.ContextoRede;
import com.lucadev.gridtopologyapi.poste.Poste;
import org.springframework.stereotype.Component;

@Component
public class GerarPostes implements GerarRede {
    @Override
    public void gerarContexto(ContextoRede rede) {
        for(int i = 0; i<=rede.getQntdPoste(); i++){
            Poste poste = new Poste();
            poste.setProjeto(rede.getProjeto());
            rede.getPostes().add(poste);
        }
    }
}
