
import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.viewmodel.CodeBlock
import com.example.viewmodel.R

class CodeBlockAdapter(val mContext: Context, val CodeBlocks: ArrayList<CodeBlock>) :
    RecyclerView.Adapter<CodeBlockAdapter.Holder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): Holder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.code_block_list, viewGroup, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // Set the name of the 'CodeBlock'
        holder.bind(CodeBlocks[position], mContext)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val func_name = itemView.findViewById<TextView>(R.id.func_name)

        fun bind(codeBlock: CodeBlock, context: Context) {
            func_name.text = codeBlock.funcName
        }
    }

    override fun getItemCount(): Int {
        return CodeBlocks.size
    }
}