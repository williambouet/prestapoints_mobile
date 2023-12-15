import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.company.prestapoints.R
import com.company.prestapoints.fragments.CategoryAdapter
import com.company.prestapoints.model.Category

class CategoryCardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val categoryList = getCategoryList()
        val adapter = CategoryAdapter(categoryList)
        recyclerView.adapter = adapter
    }

    private fun getCategoryList(): List<Category> {
        // Remplacez ces valeurs par les données réelles de vos catégories
        return listOf(
            Category(1, "Animaux", R.drawable.animaux),
            Category(6, "Jardinage", R.drawable.jardinage),
            Category(5, "Mode", R.drawable.mode),
            Category(8, "Photographie", R.drawable.photographie),
            Category(2, "Poterie", R.drawable.poterie),
            Category(3, "Restauration", R.drawable.restauration),
            Category(4, "Travaux", R.drawable.travaux),
            Category(7, "Véhicules", R.drawable.vehicules)
        )
    }
}
