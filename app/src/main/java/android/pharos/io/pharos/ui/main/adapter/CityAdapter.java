package android.pharos.io.pharos.ui.main.adapter;

import android.content.Context;
import android.pharos.io.pharos.R;
import android.pharos.io.pharos.data.network.models.City;
import android.pharos.io.pharos.data.network.models.Coord;
import android.pharos.io.pharos.ui.main.MainActivity;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter  {
    private final int TYPE_ONE = 1;
    private final int TYPE_TWO = 2;
    private SortedList<City> cities;
    private Context context;

    public CityAdapter(Context context) {
        this.context = context;
        cities = new SortedList<>(City.class, new SortedList.Callback<City>() {
            @Override
            public int compare(City o1, City o2) {
                int cityName=o1.getName().compareTo(o2.getName());
               return  ((cityName == 0) ? o1.getCountry().compareTo(o2.getCountry()) : cityName);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(City oldItem, City newItem) {
                return oldItem.getName().equals(newItem.getName());
            }

            @Override
            public boolean areItemsTheSame(City item1, City item2) {
                return item1.getName().equals(item2.getName());
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_single_item, parent, false);
            return new ViewHolderOne(view);
        } else if (viewType == TYPE_TWO) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new ViewHolderTwo(view);
        } else {
            throw new RuntimeException("The type has to be ONE or TWO ");
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_ONE:
                initLayoutOne((ViewHolderOne) holder, position);
                break;
            case TYPE_TWO:
                initLayoutTwo((ViewHolderTwo) holder, position);
                break;
            default:
                break;
        }
    }


    private void initLayoutOne(ViewHolderOne holder, int position) {
        final City city = cities.get(position);
        holder.city_value.setText(city.getName());
        holder.country_value.setText(city.getCountry());
        final Coord coord = city.getCoord();
        String mapStatic = "https://maps.googleapis.com/maps/api/staticmap?center=" + coord.getLat() + "," + coord.getLon() +
                "zoom=13&size=600x400" + "&key=" + context.getResources().getString(R.string.google_maps_key);

        Glide.with(context).load(mapStatic).into(holder.coverImage);

        holder.wholeContainer.setOnClickListener(v -> {
            ((MainActivity) context).goToMap(coord.getLat(), coord.getLon(), city.getName());
        });

    }

    private void initLayoutTwo(ViewHolderTwo holder, int position) {
        holder.progressBar.setIndeterminate(true);
    }


    static class ViewHolderOne extends RecyclerView.ViewHolder {
        ImageView coverImage;
        TextView city_value;
        TextView country_value;
        CoordinatorLayout wholeContainer;

        public ViewHolderOne(View itemView) {
            super(itemView);
            coverImage = itemView.findViewById(R.id.coverImage);
            city_value = itemView.findViewById(R.id.city_value);
            country_value = itemView.findViewById(R.id.country_value);
            wholeContainer = itemView.findViewById(R.id.wholeContainer);
        }
    }

    static class ViewHolderTwo extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ViewHolderTwo(View view) {
            super(view);
            progressBar = view.findViewById(R.id.progressBar1);
        }
    }

    @Override
    public int getItemViewType(int position) {
        City payload = cities.get(position);
        if (payload == null) {
            return TYPE_TWO;
        } else {
            return TYPE_ONE;
        }
    }

    //conversation helpers
    public void addAll(List<City> cityList) {
        cities.beginBatchedUpdates();
        for (int i = 0; i < cityList.size(); i++) {
            cities.add(cityList.get(i));
        }
        cities.endBatchedUpdates();
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }


    public void replaceAll(List<City> models) {
        cities.beginBatchedUpdates();
        for (int i = cities.size() - 1; i >= 0; i--) {
            final City model = cities.get(i);
            if (!models.contains(model)) {
                cities.remove(model);
            }
        }
        cities.addAll(models);
        cities.endBatchedUpdates();
    }
}
