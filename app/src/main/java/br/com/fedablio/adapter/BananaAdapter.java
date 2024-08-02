package br.com.fedablio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import br.com.fedablio.model.Piloto;
import br.com.fedablio.shd.R;

public class BananaAdapter extends ArrayAdapter<Piloto> {

    private final Context context;
    private final ArrayList<Piloto> elementos;

    public BananaAdapter(Context context, ArrayList<Piloto> elementos){
        super(context, R.layout.linha_banana, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_banana, parent, false);
        TextView tvNome = (TextView) rowView.findViewById(R.id.tvNomeLinhaBanana);
        TextView tvCanal = (TextView) rowView.findViewById(R.id.tvCanalLinhaBanana);
        TextView tvBanda = (TextView) rowView.findViewById(R.id.tvBandaLinhaBanana);
        tvNome.setText(elementos.get(position).getNome_piloto());
        tvCanal.setText(elementos.get(position).getCanal_piloto());
        tvBanda.setText(elementos.get(position).getBanda_piloto());
        return rowView;
    }

}
