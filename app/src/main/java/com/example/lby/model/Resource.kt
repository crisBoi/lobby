package com.example.lby.model

import com.example.lby.R

data class Resource(val title: String, val image: Int, val url: String) {
    companion object {
        fun generateList(): ArrayList<Resource> {
            var myList: ArrayList<Resource> = ArrayList();
            myList.add(Resource("Heart", R.drawable.heart, "https://www.youtube.com/watch?v=ebzbKa32kuk"))
            myList.add(Resource("Kidney", R.drawable.kidney, "https://www.youtube.com/watch?v=ctGkLYuUCvU"))
            myList.add(Resource("Stomach", R.drawable.gut, "https://www.youtube.com/watch?v=kvY8xvix4ME"))
            myList.add(Resource("Liver", R.drawable.liver, "https://www.youtube.com/watch?v=W5LstnDqLTY"))
            myList.add(Resource("Intestine", R.drawable.intestine, "https://youtu.be/4waSJqOEJts"))
            myList.add(Resource("Lungs", R.drawable.lungs, "https://www.youtube.com/results?search_query=lungs+anatomy"))

            return myList
        }
    }
}
