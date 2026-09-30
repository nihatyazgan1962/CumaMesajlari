package com.cumamesajlari.app.data

import com.cumamesajlari.app.model.MessageCategory
import com.cumamesajlari.app.model.MessageItem

object MessageRepository {

    val messages: List<MessageItem> = listOf(
        // ==========================================
        // 1. KUR'AN-I KERİM AYETLERİ
        // ==========================================
        MessageItem(
            id = "kuran_1",
            text = "Ey iman edenler! Cuma günü namaza çağrıldığı (ezan okunduğu) zaman, hemen Allah'ı anmaya koşun ve alışverişi bırakın. Eğer bilirseniz, elbette bu sizin için daha hayırlıdır.",
            category = MessageCategory.KURAN_AYETLERI,
            source = "Cuma Sûresi, 9. Ayet"
        ),
        MessageItem(
            id = "kuran_2",
            text = "Rabbiniz buyurdu ki: 'Bana dua edin, duanıza icabet edeyim.' Şüphesiz büyüklük taslayıp bana kulluk etmekten çekinenler, boyun bükmüş olarak cehenneme gireceklerdir.",
            category = MessageCategory.KURAN_AYETLERI,
            source = "Mü'min Sûresi, 60. Ayet"
        ),
        MessageItem(
            id = "kuran_3",
            text = "Onlar, inananlar ve kalpleri Allah'ı anmakla huzura kavuşanlardır. Biliniz ki, kalpler ancak Allah'ı anmakla huzur bulur.",
            category = MessageCategory.KURAN_AYETLERI,
            source = "Ra'd Sûresi, 28. Ayet"
        ),
        MessageItem(
            id = "kuran_4",
            text = "Kullarım sana beni sorduklarında bilsinler ki, şüphesiz ben onlara çok yakınım. Bana dua ettiği vakit dua edenin dileğine karşılık veririm.",
            category = MessageCategory.KURAN_AYETLERI,
            source = "Bakara Sûresi, 186. Ayet"
        ),
        MessageItem(
            id = "kuran_5",
            text = "Elbette zorluğun yanında bir kolaylık vardır. Gerçekten, her güçlükle beraber muhakkak bir kolaylık vardır.",
            category = MessageCategory.KURAN_AYETLERI,
            source = "İnşirah Sûresi, 5-6. Ayetler"
        ),
        MessageItem(
            id = "kuran_6",
            text = "De ki: Rabbim! Gireceğim yere dürüstlükle girmemi sağla, çıkacağım yerden de dürüstlükle çıkmamı sağla. Bana tarafından yardımcı bir güç ihsan eyle.",
            category = MessageCategory.KURAN_AYETLERI,
            source = "İsrâ Sûresi, 80. Ayet"
        ),
        MessageItem(
            id = "kuran_7",
            text = "Senden başka hiçbir ilah yoktur. Seni her türlü noksanlıktan tenzih ederim. Gerçekten ben kendi nefsime zulmedenlerden oldum.",
            category = MessageCategory.KURAN_AYETLERI,
            source = "Enbiyâ Sûresi, 87. Ayet (Hz. Yûnus'un Duası)"
        ),
        MessageItem(
            id = "kuran_8",
            text = "Allah bize yeter, O ne güzel vekildir. Ne güzel Mevlâ ve ne güzel yardımcıdır.",
            category = MessageCategory.KURAN_AYETLERI,
            source = "Âl-i İmrân Sûresi, 173. Ayet"
        ),
        MessageItem(
            id = "kuran_9",
            text = "Rabbim! Gönlüme ferahlık ver. İşimi bana kolaylaştır. Dilimdeki düğümü çöz ki sözümü iyice anlasınlar.",
            category = MessageCategory.KURAN_AYETLERI,
            source = "Tâhâ Sûresi, 25-28. Ayetler"
        ),
        MessageItem(
            id = "kuran_10",
            text = "Şüphesiz Rabbin sana verecek ve sen de hoşnut kalacaksın.",
            category = MessageCategory.KURAN_AYETLERI,
            source = "Duhâ Sûresi, 5. Ayet"
        ),

        // ==========================================
        // 2. HADİS-İ ŞERİFLER
        // ==========================================
        MessageItem(
            id = "hadis_1",
            text = "Üzerine güneşin doğduğu en hayırlı gün Cuma günüdür. Âdem o gün yaratıldı, o gün cennete konuldu ve o gün cennetten çıkarıldı.",
            category = MessageCategory.HADISLER,
            source = "Müslim, Cuma 18"
        ),
        MessageItem(
            id = "hadis_2",
            text = "Cuma gününde bir saat vardır ki, müslüman bir kul o vakitte namaz kılar ve Allah'tan bir hayır dilerse, Allah ona istediğini mutlaka verir.",
            category = MessageCategory.HADISLER,
            source = "Buhârî, Cuma 37; Müslim, Cuma 13"
        ),
        MessageItem(
            id = "hadis_3",
            text = "Cuma günü bana çokça salavat getirin. Çünkü sizin salavatınız bana arz olunur.",
            category = MessageCategory.HADISLER,
            source = "Ebû Dâvûd, Salât 201"
        ),
        MessageItem(
            id = "hadis_4",
            text = "Bir Müslümanın, yanında bulunmayan din kardeşine yapacağı dua kabul olunur. Başucunda görevli bir melek bulunur ve o kimse kardeşine her hayır dua ettikçe 'Âmîn, bir misli de sana olsun' der.",
            category = MessageCategory.HADISLER,
            source = "Müslim, Zikir 87"
        ),
        MessageItem(
            id = "hadis_5",
            text = "Kıyamet gününde insanların bana en yakın olanı, bana en çok salât ve selâm getirenidir.",
            category = MessageCategory.HADISLER,
            source = "Tirmizî, Vitir 21"
        ),
        MessageItem(
            id = "hadis_6",
            text = "Dua mü'minin silahı, dinin direği, göklerin ve yerin nurudur.",
            category = MessageCategory.HADISLER,
            source = "Hâkim, el-Müstedrek, I, 492"
        ),
        MessageItem(
            id = "hadis_7",
            text = "Müslüman, elinden ve dilinden diğer Müslümanların emniyette olduğu kimsedir.",
            category = MessageCategory.HADISLER,
            source = "Buhârî, Îmân 4; Müslim, Îmân 64"
        ),
        MessageItem(
            id = "hadis_8",
            text = "Birbirinize buğzetmeyiniz, birbirinize haset etmeyiniz, birbirinize sırt çevirmeyiniz. Ey Allah'ın kulları, kardeş olunuz!",
            category = MessageCategory.HADISLER,
            source = "Buhârî, Edeb 57; Müslim, Birr 23"
        ),
        MessageItem(
            id = "hadis_9",
            text = "Merhamet edenlere Rahmân da merhamet eder. Siz yeryüzündekilere merhamet ediniz ki, göktekiler de size merhamet etsin.",
            category = MessageCategory.HADISLER,
            source = "Tirmizî, Birr 16; Ebû Dâvûd, Edeb 58"
        ),

        // ==========================================
        // 3. RİSALE-İ NUR KÜLLİYATI'NDAN VECİZELER
        // ==========================================
        MessageItem(
            id = "risale_1",
            text = "Cumanız mübarek olsun. Cenâb-ı Hak bu mübarek gün hürmetine kalplerimizi nur-u Kur'an ile tenvir eylesin, dualarımızı dergâh-ı izzetinde kabul buyursun.",
            category = MessageCategory.RISALE_I_NUR,
            source = "Bediüzzaman Said Nursî"
        ),
        MessageItem(
            id = "risale_2",
            text = "İman hem nurdur, hem kuvvettir. Evet, hakikî imanı elde eden adam, kâinata meydan okuyabilir ve imanın kuvvetine göre, hâdisatın tazyikatından kurtulabilir.",
            category = MessageCategory.RISALE_I_NUR,
            source = "Risale-i Nur — Sözler (23. Söz)"
        ),
        MessageItem(
            id = "risale_3",
            text = "Güzel gören güzel düşünür. Güzel düşünen, hayatından lezzet alır.",
            category = MessageCategory.RISALE_I_NUR,
            source = "Risale-i Nur — Mektubat (Hakikat Çekirdekleri)"
        ),
        MessageItem(
            id = "risale_4",
            text = "Bismillah her hayrın başıdır. Biz dahi başta ona başlarız. Bil ey nefsim, şu mübarek kelime İslâm nişanı olduğu gibi, bütün mevcudatın lisan-ı haliyle vird-i zebânıdır.",
            category = MessageCategory.RISALE_I_NUR,
            source = "Risale-i Nur — Sözler (1. Söz)"
        ),
        MessageItem(
            id = "risale_5",
            text = "Dua bir sırr-ı ubudiyettir; ubudiyet ise, hâlisen livechillah olmalı. Yalnız aczini izhar edip, dua ile O'na iltica etmeli, rububiyetine karışmamalı.",
            category = MessageCategory.RISALE_I_NUR,
            source = "Risale-i Nur — Mektubat (24. Mektup)"
        ),
        MessageItem(
            id = "risale_6",
            text = "Her gecenin bir sabahı, her kışın bir baharı olduğu gibi; elbette dertlerin de bir devası, karanlıkların da bir nurlu fecri vardır. Ümitsizliğe düşmeyiniz.",
            category = MessageCategory.RISALE_I_NUR,
            source = "Risale-i Nur — Lem'alar"
        ),
        MessageItem(
            id = "risale_7",
            text = "Kâinatta en yüksek hakikat imandır, imandan sonra namazdır. Namaz kılanın diğer mubah dünyevî amelleri, güzel bir niyet ile ibadet hükmünü alır.",
            category = MessageCategory.RISALE_I_NUR,
            source = "Risale-i Nur — Tarihçe-i Hayat"
        ),
        MessageItem(
            id = "risale_8",
            text = "Bazen bir tek dua, binler belâyı def'eder ve binler rahmet kapısını açar. Cenâb-ı Hak bu nurlu Cuma gününde ettiğiniz duaları makbul eylesin.",
            category = MessageCategory.RISALE_I_NUR,
            source = "Risale-i Nur — Şualar"
        ),
        MessageItem(
            id = "risale_9",
            text = "Acaba dünyada ahiretin tarlası olan bu ömür sermayesini nerede ve nasıl sarf ediyoruz? Rabbim her anımızı rızası dairesinde geçirmeyi nasip eylesin.",
            category = MessageCategory.RISALE_I_NUR,
            source = "Risale-i Nur — Sözler"
        ),
        MessageItem(
            id = "risale_10",
            text = "Allah'a dayanan, hiçbir şeyden korkmaz ve hiçbir zaman ye'se düşmez. Tevekkül eden kazanır.",
            category = MessageCategory.RISALE_I_NUR,
            source = "Risale-i Nur — Mesnevi-i Nuriye"
        ),

        // ==========================================
        // 4. DUALI MESAJLAR
        // ==========================================
        MessageItem(
            id = "dua_1",
            text = "Allah'ım! Günahlarımızın küçüğünü büyüğünü, öncekini sonrakini, açığını ve gizlisini bağışla. Bu mübarek Cuma gününde gönüllerimizi nurlandır, hanemize huzur ve bereket ihsan eyle.",
            category = MessageCategory.DUALI,
            source = "Hayırlı ve Nurlu Cumalar"
        ),
        MessageItem(
            id = "dua_2",
            text = "Rabbim! Dertlilere deva, hastalara şifa, borçlulara eda nasip eyle. Kalplerimizi kin ve hasetten arındır, birbirimize karşı sevgi ve merhametle doldur. Cumanız mübarek olsun.",
            category = MessageCategory.DUALI,
            source = "Cuma Duası"
        ),
        MessageItem(
            id = "dua_3",
            text = "Ya Rabbi! Bizleri dünyada ve ahirette iyilik ve güzellikle mükafatlandır. Bizi, anne-babamızı ve bütün inananları bağışla. Hayırlı Cumalar dilerim.",
            category = MessageCategory.DUALI,
            source = "Dualı Cumalar"
        ),
        MessageItem(
            id = "dua_4",
            text = "Yüce Mevla'm bu kutlu günde açılan elleri, edilen niyazları dergâh-ı izzetinde kabul buyursun. Bizleri sevdiklerimizle beraber cennetinde buluştursun.",
            category = MessageCategory.DUALI,
            source = "Hayırlı Cumalar"
        ),
        MessageItem(
            id = "dua_5",
            text = "Allah'ım! Bize rızkın helal ve bereketli olanını, amelin ihlaslı olanını, ömrün hayırlı ve sıhhatli olanını nasip eyle. Cumanız hayırlara vesile olsun.",
            category = MessageCategory.DUALI,
            source = "Günün Duası"
        ),
        MessageItem(
            id = "dua_6",
            text = "Ey kalpleri evirip çeviren Allah'ım! Kalplerimizi dinin ve rızan üzere sabit kıl. Sıkıntılarımızı feraha, hüzünlerimizi sürura tebdil eyle. Hayırlı Cumalar.",
            category = MessageCategory.DUALI,
            source = "Cuma Niyazı"
        ),

        // ==========================================
        // 5. KISA & ANLAMLI
        // ==========================================
        MessageItem(
            id = "kisa_1",
            text = "Gönüller dua ile birleşince yollar sevgiye çıkar. Cumanız mübarek, dualarınız kabul olsun.",
            category = MessageCategory.KISA_OZ,
            source = "Hayırlı Cumalar"
        ),
        MessageItem(
            id = "kisa_2",
            text = "Avuçların semaya, dillerin duaya, kalplerin Mevla'ya yöneldiği bu kutlu Cuma günü bereket getirsin.",
            category = MessageCategory.KISA_OZ,
            source = "Nurlu Cumalar"
        ),
        MessageItem(
            id = "kisa_3",
            text = "Her Cuma bir diriliş, her dua bir umuttur. Umutlarınızın yeşerdiği huzurlu bir Cuma dilerim.",
            category = MessageCategory.KISA_OZ,
            source = "Cumanız Mübarek Olsun"
        ),
        MessageItem(
            id = "kisa_4",
            text = "Gözlerin nuru, kalplerin huzuru, Cuma'nın feyzi ve bereketi üzerinize olsun.",
            category = MessageCategory.KISA_OZ,
            source = "Hayırlı Cumalar"
        ),
        MessageItem(
            id = "kisa_5",
            text = "Rabbim bugün edeceğiniz tüm hayırlı duaları kabul, hanelerinizi sağlık ve neşeyle doldursun.",
            category = MessageCategory.KISA_OZ,
            source = "Hayırlı Cumalar"
        ),
        MessageItem(
            id = "kisa_6",
            text = "En güzel dua, başkalarının haberi olmadan onlar için edilen duadır. Dualarımdasınız. Hayırlı Cumalar.",
            category = MessageCategory.KISA_OZ,
            source = "Dua İle..."
        ),

        // ==========================================
        // 6. SAMİMİ (DOST & AKRABA)
        // ==========================================
        MessageItem(
            id = "samimi_1",
            text = "Kıymetli dostum; Cuma'nın rahmeti, bereketi ve mağfireti senin ve ailenin üzerine olsun. Selam ve dua ile hayırlı cumalar.",
            category = MessageCategory.SAMIMI,
            source = "Dostlara Selam"
        ),
        MessageItem(
            id = "samimi_2",
            text = "Gönlünden geçen güzelliklerin ömrüne nasip olduğu, sevdiklerinle bir arada huzur dolu bir gün dilerim. Cuman mübarek olsun.",
            category = MessageCategory.SAMIMI,
            source = "Sevgi ve Dua ile"
        ),
        MessageItem(
            id = "samimi_3",
            text = "Uzakları yakın eden, gönülleri birleştiren dualarda unutulmamak dileğiyle; Hayırlı, bereketli ve huzurlu Cumalar.",
            category = MessageCategory.SAMIMI,
            source = "Gönül Dostlarına"
        ),
        MessageItem(
            id = "samimi_4",
            text = "Yüzünüzden tebessüm, kalbinizden iman, evinizden bereket eksik olmasın. Tüm ailenize hayırlı ve huzurlu Cumalar dilerim.",
            category = MessageCategory.SAMIMI,
            source = "Ailece Selamlar"
        ),

        // ==========================================
        // 7. KANDİL & ÖZEL GÜNLER
        // ==========================================
        MessageItem(
            id = "kandil_1",
            text = "Mübarek kandil gecenizin ve Cuma'nızın feyzi, bereketi tüm İslam âleminin üzerine olsun. Dualarda buluşmak ümidiyle.",
            category = MessageCategory.KANDIL_BAYRAM,
            source = "Kandil & Cuma Tebriki"
        ),
        MessageItem(
            id = "kandil_2",
            text = "Regaip, Miraç, Berat ve Kadir gecelerinin nurlu ikliminde; bu kutlu Cuma gününün affımıza ve kurtuluşumuza vesile olmasını dilerim.",
            category = MessageCategory.KANDIL_BAYRAM,
            source = "Mübarek Günler"
        ),
        MessageItem(
            id = "kandil_3",
            text = "Gecesi nur, gündüzü rahmet olan bu kutlu zaman diliminde dualarınız arş-ı âlâya ulaşsın. Cumanız ve kandiliniz mübarek olsun.",
            category = MessageCategory.KANDIL_BAYRAM,
            source = "Hayırlı Kandiller"
        )
    )

    fun getByCategory(category: MessageCategory): List<MessageItem> {
        return if (category == MessageCategory.ALL) {
            messages
        } else {
            messages.filter { it.category == category }
        }
    }
}
