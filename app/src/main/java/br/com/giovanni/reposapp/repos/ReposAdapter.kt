package br.com.giovanni.reposapp.repos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.giovanni.reposapp.R
import br.com.giovanni.reposapp.api.ApiResponse
import br.com.giovanni.reposapp.api.Posts
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_repos_adapter.view.*

class ReposAdapter(private val context: Context, private val reposList: ApiResponse<Posts>) :
    RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameRepo: TextView
        val loginRepo: TextView
        val fullUserName: TextView
        val descriptionRepo: TextView
        val forks: TextView
        val stars: TextView
        val imageUser: ImageView

        init {
            nameRepo = itemView.txtNameRepo
            loginRepo = itemView.txtLoginRepo
            fullUserName = itemView.txtFullUserName
            descriptionRepo = itemView.txtDescriptionRepo
            forks = itemView.txtForks
            stars = itemView.txtStars
            imageUser = itemView.imgUser
        }
    }

    override fun getItemCount(): Int {
        return reposList.response.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.fragment_repos_adapter, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameRepo.text = reposList.response[position].name
        holder.loginRepo.text = reposList.response[position].owner.login
        holder.fullUserName.text = reposList.response[position].full_name
        holder.descriptionRepo.text = reposList.response[position].description
        holder.forks.text = reposList.response[position].forks.toString()
        holder.stars.text = reposList.response[position].stargazers_count.toString()

        Picasso.get()
            .load(reposList.response[position].owner.avatar_url)
            .placeholder(holder.imageUser.drawable)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.imageUser)
    }
}
