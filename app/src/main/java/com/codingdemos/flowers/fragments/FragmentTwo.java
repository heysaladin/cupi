package com.codingdemos.flowers.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codingdemos.flowers.DestinationData;
import com.codingdemos.flowers.MainActivity;
import com.codingdemos.flowers.MyAdapter;
import com.codingdemos.flowers.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentTwo extends Fragment {

//    private OnFragmentInteractionListener mListener;

    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    List<DestinationData> mFlowerList;
    DestinationData mDestinationData;

    public FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_two, container, false);


        // mToolbar = view.findViewById(R.id.toolbar);
        // mToolbar.setTitle(getResources().getString(R.string.app_name));

        // getActivity().setTitle("two title");

//        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//        toolbar.setTitle("Two");


        mRecyclerView = view.findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(view.getContext(), 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mFlowerList = new ArrayList<>();
        mDestinationData = new DestinationData("Masjidil Haram", "Masjidil Haram, Masjid al-Haram atau al-Masjid al-Haram (bahasa Arab: المسجد الحرام, pengucapan bahasa Arab: [ʔælmæsʤɪd ælħaram]) adalah sebuah masjid yang berlokasi di pusat kota Mekkah[1] yang dipandang sebagai tempat tersuci bagi umat Islam. Masjid ini juga merupakan tujuan utama dalam ibadah haji. Masjid ini dibangun mengelilingi Ka'bah yang menjadi arah kiblat bagi umat Islam dalam mengerjakan ibadah Salat. Masjid ini juga merupakan masjid terbesar di dunia, diikuti oleh Masjid Nabawi di Madinah al-Munawarah sebagai masjid terbesar kedua di dunia serta merupakan dua masjid suci utama bagi umat Muslim. Luas keseluruhan masjid ini mencapai 356.800 m2 (3.841.000 sq ft)dengan kemampuan menampung jamaah sebanyak 820.000 jamaah ketika musim Haji dan mampu bertambah menjadi dua juta jamaah ketika salat Id.\n" +
                "\n" +
                "Kepentingan masjid ini sangat diperhitungkan dalam agama Islam, karena selain menjadi kiblat, masjid ini juga menjadi tempat bagi para jamaah Haji melakukan beberapa ritual wajib, yaitu tawaf, dan sa'i.\n" +
                "\n" +
                "Pengertian Masjidil Haram tidak hanya diartikan sebagai masjid di kota Mekkah saja para ulama berbeda pendapat mengenai hal ini ada yang mengatakan bahwa arti masjidil haram adalah semua tempat di kota Mekkah.",
                R.drawable.masjidil_haram);
        mFlowerList.add(mDestinationData);
        mDestinationData = new DestinationData("Masjid Nabawi", "Masjid Nabawi atau Al-Masjid an-Nabawī (pengucapan bahasa Arab: [ʔælˈmæsʤɪd ælnabawī] (Arab: المسجد النبوي\u200E); Masjid Nabi) adalah sebuah masjid yang didirikan secara langsung oleh Nabi Muhammad, berlokasi di pusat kota Madinah di Arab Saudi. Masjid Nabawi merupakan masjid ketiga yang dibangun dalam sejarah Islam dan kini menjadi salah satu masjid terbesar di dunia. Masjid ini menjadi tempat paling suci kedua dalam agama Islam, setelah Masjidil Haram di Mekkah.[2] Masjid ini di buka setiap hari.\n" +
                "\n" +
                "Masjid ini sebenarnya merupakan bekas rumah Nabi Muhammad yang dia tinggali setelah Hijrah (pindah) ke Madinah pada 622 M. Bangunan masjid sebenarnya di bangun tanpa atap. Masjid pada saat itu dijadikan tempat berkumpulnya masyarakat, majelis, dan sekolah agama. Masjid ini juga merupakan salah satu tempat yang disebutkan namanya dalam Alquran. Kemajuan masjid ini tidak lepas dari pengaruh kemajuan penguasa-penguasa Islam. Pada 1909, tempat ini menjadi tempat pertama di Jazirah Arab yang diterangi pencahayaan listrik.[3] Masjid ini berada di bawah perlindungan dan pengawasan Penjaga Dua Tanah Suci.[4] Masjid ini secara lokasi berada tepat di tengah-tengah kota Madinah, dengan beberapa hotel dan pasar-pasar yang mengelilinginya. Masjid ini menjadi tujuan utama para jamaah Haji ataupun Umrah.[4] Beberapa jamaah mengunjungi makam Nabi Muhammad untuk menelusuri jejak kehidupannya di Madinah.[4]\n" +
                "\n" +
                "Setelah perluasan besar-besaran di bawah Kesultanan Umayyah al-Walid I, dibuat tempat di atas peristirahtan terakhir Nabi Muhammad beserta dua Khalifah Rasyidin Abu Bakar dan Umar bin Khattab.[5] Salah satu fitur terkenal Masjid Nabawi adalah Kubah Hijau yang berada di tenggara masjid,[6] yang dulunya merupakan rumah Aisyah,[5] dimana kuburan Nabi Muhammad berada. Pada 1279, sebuah penutup yang terbuat dari kayu di bangun dan di renovasi sedikitnya dua kali yakni pada abad ke-15 dan pada 1817.[4] Kubah yang ada saat ini dibangun pada 1818 oleh Sultan Utsmaniyah Mahmud II,[6] dan di cat hijau pada 1837, sejak saat itulah kubah tersebut dikenal sebagai \"Kubah Hijau\".",
                R.drawable.masjid_nabawi);
        mFlowerList.add(mDestinationData);
        mDestinationData = new DestinationData("Hagia Sophia", "Hagia Sophia atau Aya Sofya (dari bahasa Yunani: Ἁγία Σοφία Bizantium Yunani [aˈʝia soˈfia]; bahasa Latin: Sancta Sophia atau Sancta Sapientia; bahasa Arab: آيا صوفيا; \"Kebijaksanaan Suci\") adalah sebuah bangunan bekas basilika, masjid, dan sekarang museum, di Istanbul, Republik Turki. Dari masa pembangunannya di tahun 537 M sampai 1453 M, bangunan ini merupakan katedral Ortodoks dan tempat kedudukan Patriark Ekumenis Konstantinopel[1], kecuali pada tahun 1204 sampai 1261, ketika tempat ini diubah oleh Pasukan Salib Keempat menjadi Katedral Katolik Roma di bawah kekuasaan Kekaisaran Latin Konstantinopel. Bangunan ini menjadi masjid mulai 29 Mei 1453 sampai 1931 pada masa kekuasaan Kesultanan Utsmani. Kemudian bangunan ini disekulerkan dan dibuka sebagai museum pada 1 Februari 1935 oleh Republik Turki.[2]\n" +
                "\n" +
                "Terkenal akan kubah besarnya, Hagia Sophia dipandang sebagai lambang arsitektur Bizantium[3] dan dikatakan \"telah mengubah sejarah arsitektur.\"[4] Bangunan ini tetap menjadi katedral terbesar di dunia selama hampir seribu tahun sampai Katedral Sevilla diselesaikan pada tahun 1520.\n" +
                "\n" +
                "Bangunan yang sekarang ini awalnya dibangun sebagai sebuah gereja antara tahun 532-537 atas perintah Kaisar Rowami Timur Yustinianus I dan merupakan Gereja Kebijaksanaan Suci ketiga yang dibangun di tanah yang sama, dua bangunan sebelumnya telah hancur karena kerusuhan. Bangunan ini didesain oleh ahli ukur Yunani, Isidore dari Miletus dan Anthemius dari Tralles.[5]\n" +
                "\n" +
                "Gereja ini dipersembahkan kepada Kebijaksanaan Tuhan, sang Logos, pribadi kedua dari Trinitas Suci,[6] pesta peringatannya diadakan setiap 25 Desember untuk memperingati kelahiran dari inkarnasi Logos dalam diri Kristus.[6] Walaupun sesekali disebut sebagai Sancta Sophia (seolah dinamai dari Santa Sophia), sophia sebenarnya pelafalan fonetis Latin dari kata Yunani untuk kebijaksanaan. Nama lengkapnya dalam bahasa Yunani adalah Ναὸς τῆς Ἁγίας τοῦ Θεοῦ Σοφίας, Naos tēs Hagias tou Theou Sophias, \"Tempat Peziarahan Kebijaksaan Suci Tuhan\".[7][8]\n" +
                "\n" +
                "Pada 1453 M, Konstantinopel ditaklukkan oleh Utsmani di bawah kepemimpinan Sultan Mehmed II, yang kemudian memerintahkan pengubahan gereja utama Kristen Ortodoks menjadi masjid. Dikenal sebagai Aya Sofya dalam ejaan Turki, bangunan yang berada dalam keadaan rusak ini memberi kesan kuat pada penguasa Utsmani dan memutuskan untuk mengubahnya menjadi masjid.[9][10] Berbagai lambang Kristen seperti lonceng, gambar, dan mosaik yang menggambarkan Yesus, Maria, orang-orang suci Kristen, dan para malaikat dihilangkan atau ditutup. Berbagai atribut Keislaman seperti mihrab, minbar, dan empat menara, ditambahkan. Aya Sofya tetap bertahan sebagai masjid sampai tahun 1931 M. Kemudian bangunan ini ditutup bagi umum oleh pemerintah Republik Turki dan dibuka kembali sebagai museum empat tahun setelahnya pada 1935. Pada tahun 2014, Aya Sofya menjadi museum kedua di Turki yang paling banyak dikunjungi, menarik hampir 3,3 juta wisatawan per tahun.[11] Berdasarkan data yang dikeluarkan oleh Kementerian Budaya dan Pariwisata Turki, Aya Sofya merupakan tempat di Turki yang paling menarik perhatian wisatawan pada 2015.[12]\n" +
                "\n" +
                "Dari pengubahan awal bangunan ini menjadi masjid sampai pembangunan Masjid Sultan Ahmed (juga dikenal dengan Masjid Biru) pada 1616, Aya Sofya merupakan masjid utama di Istanbul. Arsitektur Bizantium pada Aya Sofya mengilhami banyak masjid Utsmani, seperti Masjid Biru, Masjid Şehzade (Masjid Pangeran), Masjid Süleymaniye, Masjid Rüstem Pasha, dan Masjid Kılıç Ali Pasha.",
                R.drawable.hagia_sophia);
        mFlowerList.add(mDestinationData);
        mDestinationData = new DestinationData("Colosseum", "Kolosseum adalah sebuah peninggalan bersejarah berupa arena gladiator, dibangun oleh Vespasian. Tempat pertunjukan yang besar berbentuk elips yang disebut amfiteater atau dengan nama aslinya Flavian Amphitheatre, yang termasuk salah satu dari Enam Puluh Sembilan Keajaiban Dunia Pertengahan. Situs ini terletak di kota kecil di Italia, Roma, yang didirikan oleh Wali kota Vespasian pada masa Domitianus dan diselesaikan oleh anaknya Titus[1], dan menjadi salah satu karya terbesar dari arsitektur Kerajaan Romawi yang pernah dibangun. Kolosseum dirancang untuk menampung 50.000 orang penonton.",
                R.drawable.colosseum);
        mFlowerList.add(mDestinationData);
        /*
        mDestinationData = new DestinationData("Rose", getString(R.string.description_flower_rose),
                R.drawable.rose);
        mFlowerList.add(mDestinationData);
        mDestinationData = new DestinationData("Carnation", getString(R.string.description_flower_carnation),
                R.drawable.carnation);
        mFlowerList.add(mDestinationData);
        mDestinationData = new DestinationData("Tulip", getString(R.string.description_flower_tulip),
                R.drawable.tulip);
        mFlowerList.add(mDestinationData);
        mDestinationData = new DestinationData("Daisy", getString(R.string.description_flower_daisy),
                R.drawable.daisy);
        mFlowerList.add(mDestinationData);
        mDestinationData = new DestinationData("Sunflower", getString(R.string.description_flower_sunflower),
                R.drawable.sunflower);
        mFlowerList.add(mDestinationData);
        mDestinationData = new DestinationData("Daffodil", getString(R.string.description_flower_daffodil),
                R.drawable.daffodil);
        mFlowerList.add(mDestinationData);
        mDestinationData = new DestinationData("Gerbera", getString(R.string.description_flower_gerbera),
                R.drawable.gerbera);
        mFlowerList.add(mDestinationData);
        mDestinationData = new DestinationData("Orchid", getString(R.string.description_flower_orchid),
                R.drawable.orchid);
        mFlowerList.add(mDestinationData);
        mDestinationData = new DestinationData("Iris", getString(R.string.description_flower_iris),
                R.drawable.lris);
        mFlowerList.add(mDestinationData);
        mDestinationData = new DestinationData("Lilac", getString(R.string.description_flower_lilac),
                R.drawable.lilac);
        mFlowerList.add(mDestinationData);
        */

        MyAdapter myAdapter = new MyAdapter(view.getContext(), mFlowerList);
        mRecyclerView.setAdapter(myAdapter);


//        getActivity().setTitle("Team B");

        return view;


    }

    public void onResume(){
        super.onResume();
        Log.d("LOG", "RESUME two");

        // Set title bar
//        ((MainActivity) getActivity()).setActionBarTitle("two title");
//        ((MainActivity) getActivity()).getSupportActionBar().setTitle("two title");

    }


//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    public interface OnFragmentInteractionListener {
//        public void onFragmentInteraction(String title);
//    }


    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            ((MainActivity) getActivity()).setActionBarTitle("two title");
//            ((MainActivity) getActivity()).setFragment(2);
            Log.d("LOG", "two");
        }
    }


}