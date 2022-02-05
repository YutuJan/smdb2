package gr.sae.bootstrap;


import gr.sae.base.AbstractLogComponent;
import gr.sae.domain.Person;
import gr.sae.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Profile({"dev", "test"})
@RequiredArgsConstructor
public class PeopleCreatorRunner extends AbstractLogComponent implements CommandLineRunner {
    @Autowired private PersonService personService;

    private final String BIO_000 = "After first establishing his martial arts prowess in his native Hong Kong," +
            " actor-choreographer-director Jackie Chan took his massive success in Southeast Asia and became a" +
            " huge international star, particularly in America. With a reputation as an unrelenting performer" +
            " willing to risk bodily injury - both with himself and his fellow stuntmen - to create elaborate" +
            " and jaw-dropping action sequences, Chan amazed critics and audiences with his sheer technical skill" +
            " while redefining Hong Kong action movies by bringing in an element of comedy. He spent the first" +
            " couple of decades finding his footing, but had a major breakthrough with the action-comedy \"Snake" +
            " in the Eagle's Shadow\" (1978), which propelled the previously-struggling performer into the" +
            " limelight. Though he took a shot at Hollywood with \"Battle Creek Brawl\" (1980) and \"The Cannonball" +
            " Run\" (1981), he would have to wait until \"Rumble in the Bronx\" (1996) to make his mark in the" +
            " United States. But it was his starring turn in the wildly popular \"Rush Hour\" (1998) and its" +
            " sequels that cemented his place as one of Hollywood's elite action stars. His status as a bankable" +
            " actor was further enhanced with \"Shanghai Noon\" (2001) and its follow-up, \"Shanghai Knights\"" +
            " (2003), though he took a stumble with \"Around the World in 80 Days\" (2004). While he returned" +
            " to Hong Kong for a number of films, including his first with Jet Li, \"The Forbidden Kingdom\"" +
            " (2008), Chan remained busy in Hollywood, as he retained his hold on being a popular box office draw.";
    private final String BIO_001 = "Well-known for his trademark rapid-fire wisecracks, actor-comedian Chris" +
            " Tucker broke into movies in the mid-1990s, following a successful career in stand-up. After his" +
            " scene-stealing turn in director F. Gary Gray's 1995 cult comedy hit \"Friday,\" Tucker took on" +
            " the role of his career as Jackie Chan's comic foil and high-pitched partner in the action-comedy," +
            " \"Rush Hour\" (1998). Thanks to the worldwide success of \"Rush Hour,\" Tucker became the fastest" +
            " actor ever to make Hollywood's elite \"$20 Million Club\" - the princely sum he received for" +
            " appearing in the 2001 sequel. The comedian's star power remained significant enough to once again" +
            " command a $20 million payday for the inevitable sequel, \"Rush Hour 3\" (2007). Tucker laid low" +
            " following its modest success for a couple of years, but reemerged in 2011 to reignite his stand-up" +
            " career, reminding fans of what he had always done best.";
    private final String BIO_002 = "The tempestuous screen image of two-time Academy Award winner and Renaissance" +
            " man Anthony Quinn at times seemed to mirror the prolific actor's much publicized, unquenchable" +
            " thirst for life. His exotic background enabled him to play a nearly limitless variety of ethnic" +
            " characters, ranging from Crazy Horse in \"They Died with Their Boots On\" (1942), to the marauding" +
            " Mongol warrior in \"Attila\" (1955), to an Eskimo in \"The Savage Innocents\" (1961). An" +
            " accomplished artist and painter in his own right, it came as no surprise when he embraced the role" +
            " of impressionist Paul Gauguin in \"Lust for Life\" (1956), a role that won him his second Oscar." +
            " It was, however, for his embodiment of the garrulous \"Zorba the Greek\" (1964) that Quinn would" +
            " be forever remembered, so perfectly did he capture the free-spirited, unrestrained nature of the" +
            " irascible character. Incredibly prolific, he continued to work steadily over the decades, appearing" +
            " in such films as \"The Greek Tycoon\" (1978) and the telepic adaptation of \"Ernest Hemingway's" +
            " The Old Man and the Sea\" (NBC, 1990). A man of deep appetites and diverse passions, both in film" +
            " and in his own life, Anthony Quinn became one of cinema's most beloved and respected actors in a" +
            " career that spanned nearly 70 years and more than a 150 memorable performances. ";
    private final String BIO_003 = "Canadian actor Keanu Reeves enjoyed a protean film career that encompassed" +
            " broad comedies like \"Bill and Ted's Excellent Adventure\" (1988), action blockbusters like \"Speed\"" +
            " (1994), \"The Matrix\" (1999) and \"John Wick\" (2015) and arthouse projects like \"My Own Private" +
            " Idaho\" (1990) over the course of several decades. Born Keanu Charles Reeves on September 2, 1964" +
            " in Beirut, Lebanon, he was the son of Samuel Nowlin Reeves, Jr., an American of Chinese-Hawaiian," +
            " English, Irish and Portuguese descent, and Patricia Taylor, who hailed from England. His parents" +
            " separated when Reeves was three years of age, and Taylor, who worked as a costume designer, took" +
            " their son with her on various assignments in Sydney, Australia and New York City before settling" +
            " in Toronto, Ontario. There, Reeves' mother would marry and divorce three more husbands, while her" +
            " son, raised primarily by grandparents and nannies, cycled through several Toronto high schools" +
            " while nurturing an interest in hockey. But roles in theater productions convinced him to pursue" +
            " a career as an actor, and he soon logged appearances in local television commercials and episodic" +
            " series. After making his feature film debut as a Quebecois goalie in the Rob Lowe hockey drama," +
            " \"Youngblood\" (1986), Reeves headed south to Los Angeles, where he found steady work in various" +
            " American TV-movies and episodic programs. But after earning solid notices for a dramatic turn as" +
            " a thoughtful teenager caught up in a murder in Tim Hunter's \"The River's Edge\" (1986), Reeves" +
            " graduated to features, where his quiet intensity and exotic good looks initially minted him as a" +
            " romantic juvenile lead or bruised antihero in films like \"Dangerous Liaisons\" (1988) and" +
            " \"Permanent Record\" (1988). But he showed an unexpected knack for comedy with his turn as a" +
            " good-natured, if slow-witted teen traveling through time in \"Bill and Ted's Excellent Adventure\"" +
            " (1989), which led to a slew of roles as goofy but earnest young men in Ron Howard's \"Parenthood\"" +
            " (1989) and the Lawrence Kasden-penned \"I Love You To Death\" (1990). Reeves proved to be almost" +
            " too adept at these roles, which undermined efforts to play more serious roles, like his surfing FBI" +
            " agent in Kathryn Bigelow's \"Point Break\" (1991), Englishman Jonathan Harker in \"Bram Stoker's" +
            " Dracula\" (1992) for Francis Ford Coppola, and a Shakespearean villain in Kenneth Branagh's \"Much" +
            " Ado About Nothing\" (1993), for which he was the subject of much critical scorn. But a turn as" +
            " a gay hustler in Gus Van Sant's \"My Own Private Idaho\" (1992) silenced a few critics, as did" +
            " the box office success of \"Speed\" (1994), with Reeves as a stalwart police officer helping Sandra" +
            " Bullock pilot a runaway bus. To the surprise of many industry observers, Reeves turned down the" +
            " inevitable sequel to \"Speed\" in favor of touring with his band, Dogstar, and appearing in a 1995" +
            " production of \"Hamlet\" in Winnipeg. When he returned to Hollywood, Reeves continued to keep his" +
            " distance from traditional studio fare, preferring instead to devote his energies to arthouse" +
            " efforts like \"A Walk in the Clouds\" (1995) for Alfonso Arau and Bernardo Bertolucci's \"Little" +
            " Buddha\" (1995), which cast him as the adult Buddha. Neither of these efforts, or subsequent" +
            " projects like \"Feeling Minnesota\" (1996), found much support at the box office, and he was a" +
            " supporting player in two of his most successful projects of this period - \"The Devil's Advocate\"" +
            " (1997), with Al Pacino, and \"The Replacements\" (2000), with Gene Hackman. A personal tragedy in" +
            " the loss of a child with longtime significant other Jennifer Syme, who would herself pass away two" +
            " years later, added a layer of emotional loss to his downward spiraling fortunes, but in 1999, he" +
            " rebounded as Neo, a hacker who discovers a secret world within computer technology in \"The" +
            " Matrix.\" An unqualified hit for its remarkable special effects and imaginative scripting, it was" +
            " followed by two blockbuster sequels (\"The Matrix Reloaded\" and \"The Matrix Revolutions,\" both" +
            " 2003), and with the success of Nancy Meyers' comedy \"Something's Gotta Give\" (2003), with Reeves" +
            " as Jack Nicholson's romantic rival for Diane Keaton, and \"Constantine\" (2005), with Reeves as DC" +
            " Comics' demon hunter, he had returned as a bankable box office star. Reeves soon settled into a" +
            " breathless schedule of studio and independent efforts, ranging from a remake of \"The Day the Earth" +
            " Stood Still\" (2008) and \"The Lake House\" (2008), which reunited him with \"Speed\" star Sandra" +
            " Bullock, to Richard Linklater's animated adaptation of Philip K. Dick's \"A Scanner Darkly\" (2006)" +
            " and the martial arts actioner \"The 47 Ronin\" (2013). He also branched out to embrace a variety of" +
            " projects, from a picture book for grown-ups to producing the digital filmmaking documentary \"Side" +
            " by Side\" (2011) and making his directorial debut with \"Man of Tai Chi\" (2013). The U.S.-Chinese" +
            " co-production, inspired in part by the life of Reeves' friend, stuntman Tiger Chen, took five years" +
            " to complete, which Reeves funded through steady work in dozens of features. The most unexpectedly" +
            " successful of these was \"John Wick\" (2015), directed by stunt man Chad Stahelski, who was also" +
            " Reeves' stunt double. The film, an ultra-violent tribute to noir and martial arts films, starred" +
            " Reeves as a former hitman who waged war on the Russian gangster that stole his car and killed his" +
            " dog. Its stylistic excesses drew positive reviews and $88 million at the box office, and was followed" +
            " by two more hit sequels, \"John Wick: Chapter 2\" (2017) and \"John Wick: Chapter 3-Parabellum\"" +
            " (2019). Between these efforts, Reeves collaborated with director Nicholas Winding Refn on his" +
            " surreal \"Neon Demon\" (2016), gave an amusing cameo as an exaggerated version of himself in the" +
            " Netflix feature \"Always Be My Maybe\" (2019) and lent his voice to a Canadian stunt rider toy" +
            " in \"Toy Story 4\" (2019).";
    private final String BIO_004 = "A physically striking turn as a hacker-turned-freedom fighter in the" +
            " \"Matrix\" trilogy helped to establish Carrie-Ann Moss's busy career in films and on television," +
            " which included lead and character turns in \"Memento\" (2000), \"Jessica Jones\"" +
            " (Netflix, 2015-18) and \"Tell Me a Story\" (CBS All Access, 2018- ). Born August 21," +
            " 1967 in Burnaby, the third-largest city in British Columbia, Canada, Moss and her brother," +
            " Brooke, were raised by their mother, Barbara, who nurtured her daughter's early interest in" +
            " performance through children's theater and choir in Vancouver. In her twenties, she paused" +
            " briefly in her pursuit of a professional acting career to work as a model in Japan and Europe;" +
            " while in Spain, she landed her first television role in \"Dark Justice\" (CBS, 1991-93), an" +
            " American crime series filming in Barcelona. She left the series shortly after production moved" +
            " to Los Angeles, California, and Moss worked steadily, if unsuccessfully, in a string of U.S. and" +
            " Canadian television series, most notably Aaron Spelling's short-lived \"Models, Inc.\"" +
            " (Fox, 1994-95), and the occasional feature, beginning in 1994 with the Billy Zane action-thriller" +
            " \"Flashfire.\" In 1999, Moss won her breakout role as Trinity, a leather-clad freedom fighter" +
            " aiding Keanu Reeves' battle against unseen forces in \"The Matrix\" (1999). The groundbreaking" +
            " science fiction thriller was a worldwide success, and provided Moss with an entryway into A-list" +
            " features: she won an Independent Spirit Award as a mysterious bartender in Christopher Nolan's" +
            " breakout film \"Memento\" (2000); a pious Frenchwoman in Lasse Hallstrom's \"Chocolat\" (2000)," +
            " a Mars colonist in \"Red Planet\" (2000) and a police detective in \"The Crew\" (2000), a crime" +
            " comedy with Burt Reynolds and Richard Dreyfuss. Moss returned to the \"Matrix\" franchise for \"The" +
            " Matrix Reloaded\" and \"The Matrix Revolutions,\" both shot back-to-back and released to stellar" +
            " box office returns in 2003. She then returned briefly to studio features with the thriller \"Suspect" +
            " Zero\" (2004) before settling into a string of sensitive leads and character roles in critically" +
            " acclaimed independent features, including \"Chumscrubber\" (2005), the zombie comedy \"Fido\" (2006)" +
            " with Billy Connolly, and the Canadian drama \"Snow Cake\" (2006), which earned her a Genie Award for" +
            " Best Supporting Actress. But subsequent features, including the thriller \"Disturbia\" (2007) and" +
            " \"Fireflies in the Garden\" (2008), with Julia Roberts, found little favor with audiences, and by" +
            " the mid-2010s, Moss was finding more substantive work on television. She enjoyed a four-episode" +
            " arc as a tough spy chief on \"Chuck\" (NBC, 2007-2012), played an assistant district attorney on" +
            " the short-lived \"Vegas\" (CBS, 2013), and essayed attorney Jeri Hogarth on several of Netflix's" +
            " Marvel Comics series, including \"Jessica Jones,\" \"Daredevil\" (2015-18) and the miniseries \"The" +
            " Defenders\" (2017). After returning to features with the surprise box office hit \"The Bye Bye" +
            " Man\" (2017), Moss took the lead in two television series: she was a former FBI agent working with" +
            " a Scandinavian detective in the Norwegian television series \"Wisting\" (MTG, 2019- ) and a single" +
            " mother in the second season of CBS All Access's \"Tell Me a Story.\" ";
    private final String BIO_005 = "A versatile performer equally adept at playing both hero and villain, actor" +
            " Laurence Fishburne emerged on the scene after famously lying about his age to Francis Ford Coppola" +
            " in order to land a prominent part in the director's seminal \"Apocalypse Now\" (1979). Though he" +
            " struggled in small parts over the ensuing decade, Fishburne came into his own as a father trying" +
            " to keep his son out of the gang life in \"Boyz n the Hood\" (1991). After richly textured supporting" +
            " roles in \"What's Love Got to Do with It\" (1993) and \"Searching for Bobby Fisher\" (1993), he" +
            " became the first African-American actor to perform the title role of \"Othello\" (1995) for a major" +
            " Hollywood studio. Toward the end of the decade, Fishburne achieved iconic status with his Zen-like" +
            " performance as the leather-clad Morpheus in the cultural phenomenon, \"The Matrix\" (1999), a role" +
            " he reprised in the back-to-back sequels. With appearances in movies like \"Mission: Impossible III\"" +
            " (2006), \"Bobby\" (2006) and \"Assault on Precinct 13\" (2005), as well as taking over the central" +
            " role on \"CSI: Crime Scene Investigation\" (CBS, 2000-2015) and both co-starring in and co-producing" +
            " the sitcom \"black-ish\" (ABC 2014- ), Fishburne solidified his legacy as a truly diverse and" +
            " welcome performer.";
    private final String BIO_006 = "A combination of determination, personality, and sheer brawn helped to propel" +
            " Arnold Schwarzenegger from the top of the bodybuilding world to Hollywood stardom and later, political" +
            " power as the governor of the state of California. Born Arnold Alois Schwarzenegger in the Austrian" +
            " village of Thal on July 30, 1947, he was the son of the town's chief of police, a strict disciplinarian" +
            " who reportedly did not believe that Schwarzenegger was his biological child. A middling student but an" +
            " enthusiastic sportsman, Schwarzenegger discovered bodybuilding in his teenage years, and began" +
            " competing at a professional level at the age of 17. Inspired by the careers of such former" +
            " bodybuilders turned actors as Steve Reeves, he won his Junior Mr. Europe title a year later while" +
            " completing his required service in the Austrian military, and traveled to England to in 1966 to" +
            " compete in his first Mr. Universe contest. After earning second place, he trained extensively with," +
            " among others, three-time Mr. Universe Reg Park, who helped to guide Schwarzenegger to his first Mr." +
            " Universe title in 1967. The following year, he relocated to the United States, where he trained at" +
            " the fmed Gold's Gym in Venice, California with Muscle and Fitness publisher Joe Weider, who also" +
            " created the Mr. Olympia competition. Schwarzenegger lost his first bid for that title in 1969, but" +
            " returned the following year to earn his first Mr. Olympia, and would repeat that success from 1971" +
            " to 1974 before announcing his retirement from bodybuilding. He was convinced to compete for a fifth" +
            " title by documentarians George Butler and Robert Fiore, who filmed his training regimen for their" +
            " 1975 \"Pumping Iron\" The documentary, which pitted a slimmer Schwarzenegger against the bigger," +
            " brawnier Lou Ferrigno, underscored his natural ease and magnetism in front of the camera, and helped" +
            " to pave the way for Schwarzenegger's film career after he captured the 1975 and 1980 titles. He had" +
            " already appeared on film, first in the title role for \"Hercules in New York\" (1969), where his" +
            " thick Austrian accent was replaced by the voice of another actor, and then as a deaf-mute in Robert" +
            " Altman's \"The Long Goodbye\" (1973). But with the success of \"Pumping Iron,\" more substantive" +
            " roles began to open up for Schwarzenegger: he won a Golden Globe as a cheerful bodybuilder opposite" +
            " Jeff Bridges and Sally Field in Bob Rafelson's \"Stay Hungry\" (1976), played broadly opposite Kirk" +
            " Douglas in the cartoonish \"The Villain\" (1979), and appeared as one of his bodybuilding idols," +
            " Mickey Hargitay, in the ABC TV-movie \"The Jayne Mansfield Story\" (1979). But in 1982, he found" +
            " his star-making role as Robert E. Howard's legendary warrior-king, Conan, in John Milius' 'Conan the" +
            " Barbarian\" (1982). After becoming an American citizen in 1983, and reprising Conan in a 1984 sequel," +
            " \"Conan the Destroyer\" (1984), Schwarzenegger accepted a largely silent role in a low-budget" +
            " science fiction thriller called \"The Terminator\" (1984). A tense, action-packed crowd-pleaser," +
            " the film cemented Schwarzenegger's star status (and launched the directorial career of James" +
            " Cameron), and led to a string of popular action films, including \"Commando\" (1985), \"The Running" +
            " Man\" (1987) and \"Predator\" (1988), which helped to mint Schwarzenegger - who had been written" +
            " off by Hollywood due to his build and accent in the early '70s - as one of the biggest box office" +
            " draws in the United States. During this period, he also joined one of the most famous political" +
            " and social dynasties in American history when he married journalist Maria Shriver in 1986. Perhaps" +
            " as a nod to his deepening roots in mainstream America, Schwarzenegger began adding more" +
            " family-friendly fare to his movie c.v: unlike many of his fellow action heroes like Sylvester" +
            " Stallone, Schwarzenegger imbued his roles with a sense of humor, which helped to pave the way" +
            " for Ivan Reitman's comedy \"Twins\" (1988), which imagined him as the separated-at-birth twin of" +
            " Danny DeVito. A sizable hit, it led to more comic efforts, including \"Kindergarten Cop\" (1990)" +
            " and \"Junior\" (1994), which netted him a Golden Globe nomination, though he continued to mine the" +
            " action vein with films like \"Total Recall\" (1990). The following year, he reprised his implacable" +
            " robotic character, the T-800, in Cameron's \"Terminator 2: Judgment Day,\" which was the highest" +
            " grossing film of 1991. But after scoring a second box office hit with Cameron in \"True Lies\"" +
            " (1994), Schwarzenegger's status at the top of the movie heap began to falter; action and comedy" +
            " efforts like \"Eraser\" (1996) and Batman & Robin\" (1997) were met with less than enthusiastic" +
            " reviews and ticket sales. He returned to the Terminator franchise again in \"Terminator 3: Rise" +
            " of the Machines\" (2003) before largely retiring from the film business. But in 2003, the second" +
            " phase of his extraordinary career began when he announced his candidacy for governor of California" +
            " during its historic election recall; Schwarzenegger had always harbored political intentions," +
            " and had been close to various presidents as the head of the Council on Physical Fitness and Sports," +
            " but with his win of 48.6% of the vote in 2004, he had followed in the footsteps of another idol," +
            " Ronald Reagan, and assumed leadership of one of the largest economies in the world. An avowed" +
            " Republican, Schwarzenegger slowly moved towards a more moderate position during his tenure as" +
            " governor, embracing actions against climate change and supporting an emissions trading scheme" +
            " with other U.S. states. Decisions such as these did not win him support among Republicans, and" +
            " accusations of drug use and sexual harassment prior to his gubernatorial run dogged him throughout" +
            " his tenure in Sacramento. He eventually left office with basement-level approval ratings in 2011," +
            " and took further brickbats when his 25-year marriage to Shriver ended after the Los Angeles Times" +
            " revealed that he had fathered a son with his family's housekeeper. He made a bid to return to" +
            " acting, initially in action films like the all-star \"Expendables\" (2012) series, but later," +
            " in a string of smaller, more character-driven action and crime films like \"Sabotage\" (2014)" +
            " and the horror film \"Maggie\" (2015), which saw Schwarzenegger give an impressive turn as a" +
            " father protecting his daughter as she underwent a monstrous transformation. Most, if not all," +
            " were only modest box office successes, including his return to the \"Terminator\" franchise with" +
            " \"Terminator Genisys\" in 2015, and his single season as the host of \"The New Celebrity" +
            " Apprentice\" (NBC, 2017) - replacing Donald Trump - was met with disinterest. But Schwarzenegger" +
            " remained active, both in front of the camera and behind the scenes in a variety of capacities," +
            " including the Schwarzenegger Institute for State and Global Policy, which sought to find common" +
            " ground between political parties, and various climate change initiatives. In 2019, he again returned" +
            " to the \"Terminator\" franchise, along with James Cameron and co-star Linda Hamilton, for" +
            " \"Terminator: Dark Fate.\"";
    private final String BIO_007 = "Her genre success overshadowed her impressive range, but Linda Hamilton enjoyed" +
            " a remarkable and varied career. Trained at the Lee Strasberg Theatre Institute, she became a movie" +
            " star as Sarah Connor, the mother-to-be of humanity's last hope in James Cameron's action/sci-fi" +
            " masterpiece, \"The Terminator\" (1984), and then won over an entirely new set of fans as a doomed" +
            " lover in the lushly romantic urban fantasy \"Beauty and the Beast\" (CBS, 1987-1990) opposite Ron" +
            " Perlman. Her impressive turn - and even more impressive muscular physique - as the ultimate warrior" +
            " woman in \"Terminator 2: Judgment Day\" (1991) ensured her a measure of cinematic immortality and" +
            " marked her greatest professional triumph. She made headlines with her marriage to (and lucrative" +
            " divorce from) James Cameron, and notched a series of well-received, award-winning TV-movie and" +
            " voiceover roles, but made her biggest subsequent impact as a vocal advocate for mental illness" +
            " issues when she publicly revealed her lifelong struggle with bipolar disorder. Hamilton was a" +
            " talented, tough actress who deserved a bigger career beyond her iconic genre work, but made the" +
            " most of the one she had.";
    private final String BIO_008 = "Having become a highly successful model at the young age of 11, actress Milla" +
            " Jovovich transitioned to the big screen, where she managed to parlay that success into other side" +
            " careers, including musician and fashion designer. After gracing the covers of such high-profile" +
            " fashion magazines as Vogue and Mademoiselle, Jovovich landed her first major feature playing the" +
            " lead in \"Return to the Blue Lagoon\" (1991), which earned its share of controversy for displaying" +
            " the 15-year-old nude onscreen. She had a significant co-starring turn in the acclaimed \"Chaplin\"" +
            " (1992) before having her screen time drastically cut to a bare minimum for \"Dazed and Confused\"" +
            " (1993), even though the actress was prominently featured in the movie's advertising. Following a" +
            " brief hiatus from filmmaking to focus on creating music, an adult Jovovich returned to features" +
            " and reimaged herself as an action heroine with her turn in Luc Besson's sci-fi action flick, \"The" +
            " Fifth Element\" (1997). But it was her turn as the amnesiac Alice in \"Resident Evil\" (2002) that" +
            " helped her win a cult following among sci-fi horror aficionados. Jovovich enjoyed a newfound status" +
            " as a female action star on par with the likes of Linda Hamilton and Sigourney Weaver.";
    private final String BIO_009 = "Armed with good looks, an everyman appeal, and boundless self-confidence," +
            " actor Bruce Willis shot from obscurity to superstar status via both a hit television series and one" +
            " of the most popular action-adventure films of all time. After beating out literally thousands of" +
            " contenders, Willis' portrayal of wisecracking P.I. David Addison on \"Moonlighting\" (ABC, 1985-89)" +
            " made him one of television's hottest leading men. It was, however, the character of indefatigable" +
            " Det. John McClain in the blockbuster actioner \"Die Hard\" (1988) that solidified Willis as a" +
            " legitimate action star - albeit one who ends up broken and bruised. There were periodic career" +
            " slumps ahead, as evidenced by the colossal failures of \"The Bonfire of the Vanities\" (1990) and" +
            " \"Hudson Hawk\" (1991). Still, as doggedly determined as McClane, Willis followed each low with" +
            " stellar turns in successes like director Quentin Tarantino's \"Pulp Fiction\" (1994). Working with" +
            " cinema's most commercially successful filmmakers, Willis helped deliver box office winners like" +
            " director Michael Bay's \"Armageddon\" (1998) and the landmark thriller, \"The Sixth Sense\" (1999)." +
            " Despite the end of his 13-year marriage to actress Demi Moore, the actor channeled his sense of" +
            " humor in the comedy \"The Whole Nine Yards\" (2000) to fine effect. More than 20 years after entering" +
            " the limelight, Willis remained one of film's most bankable stars in such hits as \"Sin City\" (2005)," +
            " \"Live Free or Die Hard\" (2007) and \"Red\" (2010). Working constantly and playing to his strengths," +
            " the witty Willis enjoyed a career longevity that other action movie stars could only envy.";
    private final String BIO_010 = "Sylvester Stallone was an icon of American cinema who, over the course of his" +
            " nearly half-century in the film industry, was singlehandedly responsible for giving life to two of" +
            " the most beloved characters to have ever graced the silver screen: Rocky Balboa and John Rambo." +
            " Born in the Hell's Kitchen neighborhood of Manhattan, Stallone was left paralyzed in parts of face" +
            " due to complications during his birth. By all accounts, Stallone also had a difficult childhood. His" +
            " parents had a volatile relationship and fought all the time. They eventually got divorced. So, after" +
            " living for a number of years in the Washington, D.C. area, Stallone moved to Philadelphia to live" +
            " with his mother and her second husband. It was in the City of Brotherly Love that Stallone would" +
            " attend high school, and first become interested in acting. The teen Stallone may not have known it" +
            " at the time, but Philadelphia would also go on to become the location for his most memorable film," +
            " 1976's \"Rocky.\" After graduating from high school Stallone went on to pursue his studies in" +
            " dramatic arts, first at the American College in Switzerland, and then later the University of Miami." +
            " It was at the University of Miami that he would go on to earn his bachelor's degree. After college" +
            " he moved to New York City to try and make it as an actor. Stallone took on a number of odd jobs during" +
            " these salad days to pay the bill, including working in the cleanup crew for the lion's cages at the" +
            " Central Park Zoo. In 1970, in the fledgling days of his acting career, Stallone would appear in the" +
            " soft-core porn film \"Party at Kitty and Stud's\" (1970). He also made uncredited appearances in the" +
            " Jane Fonda drama \"Klute\" (1971), as well as the Woody Allen comedy \"Bananas\" (1971). In 1974" +
            " Stallone earned praise for his portrayal of a 50s street tough in the film \"The Lords of Flatbush\"" +
            " (1974). It was also around this period that Stallone began writing a screenplay about an underdog" +
            " Philadelphia boxer who \"goes the distance\" in the ring with the heavyweight champion. After" +
            " finishing the script, which he called \"Rocky,\" Stallone shopped it around Hollywood. Several" +
            " producers took an interest in making the film. Stallone, however, had one condition: he and only he" +
            " could play the title role. He eventually found two producers, Irwin Winkler and Robert Chartoff, to" +
            " agree, and when \"Rocky\" was released in 1976 it quickly became a sensation. The film was nominated" +
            " for 10 Oscars, including two for Stallone for acting and writing, and would go on to take home the" +
            " Academy Award for Best Picture. Emboldened by the success of \"Rocky,\" Stallone's career took off." +
            " He wrote and directed two \"Rocky\" sequels in the ensuing years, \"Rocky II\" (1979) and \"Rocky" +
            " III\" (1982), and went on to earn critical praise for his performances in \"Paradise Alley\" (1978)" +
            " and \"Nighthawks\" (1981). Then in 1982 Stallone's career hit an even higher peak when he starred in," +
            " and wrote the screenplay for, the action film \"First Blood\" (1982). That film would introduce a new" +
            " iconic movie character to popular culture, John Rambo, thus making Stallone one of the biggest box" +
            " office draws of the 1980s. Stallone would write and appear in two more \"Rambo\" movies in the 1980s," +
            " \"Rambo: First Blood Part II\" (1985) and \"Rambo III\" (1988), in addition to two more \"Rocky\"" +
            " sequels. Throughout the 1980s Stallone starred in a number of other blockbuster action films, like" +
            " \"Cobra\" (1986), \"Over the Top\" (1987), and \"Lock Up\" (1989). By the mid-1990s Stallone's career" +
            " began to hit a low point after he appeared in the flops \"Judge Dredd\" (1995) and \"Daylight\"" +
            " (1997). However, by the early 2000s his career began to regain some steam with his roles in films" +
            " like \"Get Carter\" (2000) and \"Driven\" (2001). In 2006 Stallone resuscitated the Rocky character" +
            " for \"Rocky Balboa\" (2006), the sixth film in the Rocky franchise and the first since 1990's badly" +
            " received \"Rocky V\" (1990). \"Rocky Balboa,\" which Stallone also directed, drew almost unanimous" +
            " praise from both fans and critics alike, and was seen as a fitting return to form for one of the big" +
            " screen's most enduring characters. In 2008 Stallone would also go onto revive John Rambo in the action" +
            " movie \"Rambo\" (2008), the fourth film in the \"Rambo\" series. In 2010 Stallone would launch" +
            " another action franchise by writing, directing and starring in \"The Expendables\" (2010). The film" +
            " featured a who's who of action stars, including Jason Statham, Jet Li, and Dolph Lundgren, and was" +
            " a huge hit with audiences. Two \"Expendables\" sequels would follow, in 2012 and 2014, respectively" +
            ". In 2015 Stallone relaunched the \"Rocky\" franchise with \"Creed\" (2015), which centered on the son" +
            " of Rocky's nemesis from the first film, Apollo Creed. The title character was played by Michael B." +
            " Jordan, and for his role in the film, Stallone earned his second Oscar nomination for acting. In 2019," +
            " after nearly 50 years in show business, Stallone brought back John Rambo for an eagerly anticipated" +
            " fourth sequel \"Rambo: Last Blood\" (2019). The film was released in the fall of 2019, with the" +
            " 73-year-old Stallone playing the title character once again.";
    private final String BIO_011 = "Dolph Lundgren became a beloved action star in the 1980s, but his path to" +
            " stardom was not always so clear. Born in Spanga, Sweden in 1957, Lundgren took an interest in" +
            " martial arts as a child as a means of coping with his sometimes troubled relationship with his" +
            " father. He took up judo and Gōjū-ryū at age seven and karate at age 10, and later began lifting" +
            " weights as a teenager. However, after graduating from high school, Lundgren pursued a decidedly" +
            " academic path, moving to the United States to study chemical engineering at Washington State" +
            " University and Clemson University on an academic scholarship before returning to Sweden to complete" +
            " his degree at the Royal Institute of Technology in Stockholm. Lundgren then moved to Sydney," +
            " Australia, where he completed a master's degree in chemical engineering at the University of Sydney." +
            " However, during the course of his higher education, Lundgren continued studying martial arts," +
            " competing as the captain of Sweden's Kyokushin karate team at the 1979 World Open Tournament," +
            " winning the European championships in 1980 and 1981, and winning a heavyweight tournament in" +
            " Australia in 1982. In addition to both his academic and extra-curricular interests, Lundren also" +
            " earned money working as a bouncer at a Sydney nightclub. He was on the job after recently finishing" +
            " his master's degree and receiving a Fulbright scholarship to continue his graduate studies at" +
            " Boston's Massachusetts Institute of Technology when he met up-and-coming singer and model Grace" +
            " Jones. She hired him as a bodyguard in addition to the two beginning a romantic relationship, and" +
            " Lundgren opted to forgo his academic plans, instead moving with Jones to New York. There, he found" +
            " a new job as a bouncer-working alongside fellow future on-screen actor Chazz Palminteri at famous" +
            " nightclub the Limelight-and began working occasionally as a model. Soon, Jones helped Lundgren" +
            " audition for a small role in her upcoming film, the James Bond movie \"A View to a Kill\" (1985)." +
            " He won the bit part and made his debut as a KGB operative in the movie. He found the process of" +
            " filming to be surprisingly rewarding, and quickly decided to audition for another role, that of" +
            " antagonist Ivan Drago opposite Sylvester Stallone in the upcoming sequel \"Rocky VI\" (1985)." +
            " Lundgren beat out 5000 other hopefuls to win the role, which proved to be his breakout. He continued" +
            " acting even after his relationship with Jones came to an end in 1986, starring in cult favorite" +
            " \"Master of the Universe\" (1987) and as comic book character \"The Punisher\" (1989). Lundgren's" +
            " interests in bodybuilding and martial arts helped suit him to roles in action movies and he" +
            " continued this throughout the 90s, co-starring with Jean Claude Van Damme in \"Universal Soldier\"" +
            " (1992) and Keanu Reeves in \"Johnny Mnemonic\" (1995), as well as in the 2000s, starring in films" +
            " like \"The Defender\" (2004) and \"Direct Contact\" (2009). While these later films were less" +
            " prestigious, Lundgren's career entered another upswing when he appeared with several other top-tier" +
            " action stars such as Arnold Schwarzenegger and Sylvester Stallone in the ensemble hit \"The" +
            " Expendables\" (2010). He returned for \"The Expendables 2\" (2012) and \"The Expendables 3\"" +
            " (2014) during this period, in addition to appearing in movies like the crime dramedy \"War Pigs\"" +
            " (2015) and in a six-episode arc on the popular series \"Arrow\" (CW, 2012-). In 2018, Lundgren" +
            " reprised the role of Ivan Drago for the acclaimed latter-day spin-off \"Creed II\" (2018) and" +
            " also appeared as King Nereus in the superhero blockbuster \"Aquaman\" (2018). The following year" +
            " found him starring in the action thriller \"The Tracker\" (2019).";
    private final String BIO_012 = "Though acclaimed veteran actor Jeff Bridges was born into a Hollywood family," +
            " he proved his own merit as a star several times over. The son of actors Lloyd and Dorothy Bridges," +
            " Bridges was born in Los Angeles in 1949. As children, he and his brother Beau occasionally appeared" +
            " in small roles on their father's TV series \"Sea Hunt\" (CBS, 1958-1961) and \"The Lloyd Bridges" +
            " Show\" (CBS, 1962-63), and by age 17, he was touring with his father in a production of" +
            " \"Anniversary Waltz.\" When the tour wrapped, Bridges struck out on his own, studying acting" +
            " at the Herbert Bergdorf Studio before beginning to actively pursue film roles. He also enlisted" +
            " in the U.S. Coast Guard Reserve, serving from 1967 to 1975. Bridges made guest appearances on shows" +
            " like \"Lassie\" (CBS, 1954-1974) before landing his breakout role as Duane Jackson in Peter" +
            " Bogdonovich's acclaimed film \"The Last Picture Show\" (1971), for which he earned his first Oscar" +
            " nomination. Soon, he was taking direction from legendary filmmaker John Huston with a prominent" +
            " supporting role in the boxing film \"Fat City\" (1972). Bridges next starred as a NASCAR driver" +
            " in in \"The Last American Hero\" (1973) before earning yet another Oscar nod for his performance" +
            " alongside Clint Eastwood in the crime comedy \"Thunderbolt and Lightfoot\" (1974). In 1975, Bridges" +
            " appeared in a contemporary Western called \"Rancho Deluxe\" (1975) and while filming the movie in" +
            " Montana, met a waitress named Susan Geston, whom he married later that year. The couple would" +
            " maintain a home in Montana despite Bridges' demands in Hollywood for decades to come. Less than" +
            " a year after his wedding, Bridges starred in the blockbuster remake of \"King Kong\" (1976)," +
            " cementing his place in Hollywood as a major star. As the '80s began, he starred in the groundbreaking" +
            " science fiction adventure \"TRON\" (1982) and later earned his third Academy Award nomination for his" +
            " performance in the sci-fi drama \"Starman\" (1984). As the decade progressed, he would make waves in" +
            " films like \"Against All Odds\" (1984), \"Jagged Edge\" (1985), and \"The Fabulous Baker Boys\"" +
            " (1989). By the '90s, Bridges was long established as both a bankable movie star and a true actor's" +
            " actor. He worked with the respected director Peter Weir in the drama \"Fearless\" (1993) and starred" +
            " as legendary cowboy Wild Bill Hickok in the aptly titled \"Wild Bill\" (1995). But perhaps one of" +
            " Bridges' most iconic roles came in 1998 when he played Jeffrey \"The Dude\" Lebowski in the Coen" +
            " Brothers' film \"The Big Lebowski\" (1998). Bridges' performance as the lackadaisical character" +
            " struck a huge chord with audiences and quickly became an enduring classic. He continued to find" +
            " compelling roles in the following decade, earning another Academy Award nomination for his role in" +
            " the political drama \"The Contender\" (2001) and reteaming with Terry Gilliam for the hallucinatory" +
            " drama \"Tideland\" (2005). Bridges joined the Marvel universe as villain Obadiah Stane in \"Iron" +
            " Man\" (2008) and finally won an Oscar in 2010 for his critically acclaimed performance as a troubled" +
            " country musician in \"Crazy Heart\" (2010). The following year found Bridges working with the Coen" +
            " brothers again, this time playing Rooster Cogburn in a lauded remake of the Western \"True Grit\"" +
            " (2011) that earned him still another Oscar nomination. He won similar accolades for his performance" +
            " as a Texas Ranger in the neo-Western \"Hell or High Water\" (2016)-the basis for his seventh Oscar" +
            " nod. Bridges went on to join the \"Kingsmen\" action franchise with a role in \"Kingsmen: The" +
            " Golden Circle\" (2018) and memorably appeared in the ensemble thriller \"Bad Times at the El Royale\"" +
            " (2018).";
    private final String BIO_013 = "Award-winning actress Julianne Moore gradually built up an increasingly" +
            " impressive body of work to ultimately become acknowledged as one of the most talented actresses" +
            " of her generation. Emerging from the world of daytime soaps, Moore began to attract attention for" +
            " work in films like Robert Altman's \"Short Cuts\" (1993) and eventually blockbusters like Steven" +
            " Spielberg's \"The Lost World: Jurassic Park\" (1997). But it was her devastating turn as a" +
            " maternal porn star in \"Boogie Nights\" (1997) that made everyone sit up and take notice. With" +
            " a combination of supporting roles in off-beat comedies like \"The Big Lebowski\" (1998) to" +
            " starring turns in such dramatic fare as \"The End of the Affair\" (1999) and \"Magnolia\"" +
            " (1999), Moore found herself in ever-increasing demand. With a pair of characters exploring the" +
            " despair of two separate 1950s suburban housewives in the acclaimed dramas \"Far From Heaven\" (2002)" +
            " and \"The Hours\" (2002), she racked up more award nominations than most performers receive in a" +
            " lifetime. Moore continued to astonish with her versatility in the dystopian thriller \"Children" +
            " of Men\" (2006), indie darling \"The Kids Are All Right\" (2010), and Alzheimer's drama \"Still" +
            " Alice\" (2014), for which she won her first Academy Award. During this time, she also appeared" +
            " in the key role of President Coin in the final two films in the blockbuster \"The Hunger Games\"" +
            " franchise. On the small screen, Moore topped herself with an uncanny, Emmy-winning portrayal of" +
            " former vice presidential candidate Sarah Palin in \"Game Change\" (HBO, 2012) and a recurring comic" +
            " role as Jack Donaghy's old childhood sweetheart on \"30 Rock\" (NBC 2006-2013), showing yet again" +
            " that she continually defied expectations and reinvented herself with nearly every role.";
    private final String BIO_014 = "After landing his first on-screen role with his very first audition, Rami" +
            " Malek went onto play the first openly gay teenager in a network family sitcom, receive critical" +
            " acclaim for his performances in \"The Pacific\" (HBO 2010) and \"Short Term 12\" (2013) and appear" +
            " in several of the biggest film franchises of the 21st century. Born in Los Angeles in 1981, Malek" +
            " graduated from the University of Evansville, IN, with a bachelor of fine arts degree in 2003 and" +
            " initially focused on theatre work before making his television debut as Andy in \"Gilmore Girls\"" +
            " (The WB 2000-07). Malek then guested on supernatural crime drama \"Medium\" (NBC 2005-2011) and" +
            " appeared as Iraqi insurgent Hassan in controversial war drama \"Over There\" (FX 2005) before taking" +
            " on the more notable role of Kenny, the gay best friend of the Gold family's oldest son Larry in the" +
            " much-maligned sitcom \"The War At Home\" (Fox 2005-07). Malek then adopted an English accent to" +
            " play pharaoh Ahkmenrah in the family adventure hit \"Night At The Museum\" (2006), a character he" +
            " would also reprise in the sequel \"Battle Of The Smithsonian\" (2009), before returning to the" +
            " stage in Keith Bunin's \"The Credeaux Canvas.\" Following a three-episode stint as Egyptian-American" +
            " suicide bomber Marcos Al-Zacar in the eighth series of \"24\" (Fox 2001-2010), Malek was praised for" +
            " his performance as morally ambiguous corporal Merriell Shelton in the Emmy Award-winning miniseries" +
            " \"The Pacific\" (HBO 2010), where he caught the eye of executive producer Tom Hanks, who then cast" +
            " him as pot-stirring college student Steve Dibiasi in his second directorial effort, \"Larry Crowne\"" +
            " (2011). After guesting as serial killer Webb Porter in an episode of \"Alcatraz\" (Fox 2012), Malek" +
            " added to his filmography by playing a watch officer in \"Battleship\" (2012) and cult leader" +
            " Lancaster Dodd's son-in-law Clark in \"The Master\" (2011). Malek then gained a new fan base after" +
            " he was cast as alpha vampire Benjamin in \"The Twilight Saga: Breaking Dawn - Part 2\" (2012) and" +
            " played newly-recruited foster care worker Nate in critically-acclaimed indie drama \"Short Term 12\"" +
            " (2013). Malek next appeared as Joe Douchett's abductor Doo Doo Brown in Spike Lee's remake of Korean" +
            " hit \"Oldboy\" (2013). In 2015, he took on what would prove to be a starmaking role for him when he" +
            " was cast in the lead of the USA show \"Mr. Robot\" as paranoid hacker Elliot. The twisty drama" +
            " brought him the acclaim he had long deserved, as well as multiple award nominations. After appearing" +
            " in the little-seen surreal mystery \"Buster's Mal Heart\" (2016) and a remake of prison drama" +
            " \"Papillon\" (2017), Malek broke through to big screen stardom through his portrayal of Freddie" +
            " Mercury in the Queen biopic \"Bohemian Rhapsody\" (2018). Though the production was troubled," +
            " with director Bryan Singer being fired during the shoot and Malek taking some online mockery for" +
            " the prominent false teeth he wore to mimic Mercury's distinctive orthodontia, the film was a" +
            " massive box office success around the world. Malek won the Golden Globe for his portrayal as well" +
            " as Best Actor in the 2019 Oscars.";
    private final String BIO_015 = "Jennifer Aniston remained an A-list actor for decades, though she may always" +
            " remain best known for her role as Rachel Green on the landmark sitcom \"Friends\" (NBC, 1994-2004)." +
            " Born in Los Angeles in 1969 to actors John Aniston and Nancy Dow, Aniston became interested in her" +
            " parents' profession as a teenager, even graduating from Fiorello H. LaGuardia High School of Music" +
            " and the Performing Arts. She landed a number of roles early in her career on the TV series \"Molloy\"" +
            " (Fox, 1990), \"Ferris Bueller\" (NBC, 1990-91), \"The Edge\" (Fox, 1992-93), and \"Muddling Through\"" +
            " (CBS, 1994) but was frustrated when each series was canceled before a second season. Around this" +
            " time, Aniston was at a gas station when she ran into Warren Littlefield, then-president of" +
            " entertainment for NBC, whom she knew through her father. She confided in him her discouragement" +
            " about her many series cancellations, and he personally helped her land the role of Rachel on the" +
            " upcoming series \"Friends.\" The massively popular series made Aniston an instant star, and she" +
            " acted in movies at the same time, memorably appearing in the romantic comedy \"Picture Perfect\"" +
            " (1997), the cult hit \"Office Space\" (1999), and the Ben Stiller romcom \"Along Came Polly\" (2004)." +
            " After \"Friends\" wrapped in 2004, Aniston would continue to find success in film, starring in the" +
            " Nicole Holofcener dramedy \"Friends with Money\" (2006) and the tearjerker \"Marley and Me\" (2008)," +
            " along with wildly popular R-rated comedies like \"Horrible Bosses\" (2011), \"Wanderlust\" (2012)," +
            " and \"We're the Millers\" (2013). In 2014, Aniston gave a critically acclaimed performance as a" +
            " chronic pain sufferer in the drama \"Cake\" (2014). After turns in \"Office Christmas Party\" (2016)" +
            " and the pageant-themed drama \"Dumplin'\" (2018), Aniston joined Steve Carell and Gugu Mbatha-Raw" +
            " for the series \"The Morning Show\" (Apple TV, 2019-).";
    private final String BIO_016 = "A former model whose cover-worthy appearance belied her knack for comedy," +
            " actress Courteney Cox was among the most popular performers on television throughout the 1990s and" +
            " early millennium, thanks to her popular turn as Monica Gellar on \"Friends\" (NBC, 1994-2004). For" +
            " millions of viewers, her performance as Gellar came to symbolize the over-achiever as she struggled" +
            " to balance her career, love life and desire for a family with her host of obsessive-compulsive" +
            " quirks, much to the delight of audiences. The success of \"Friends\" made Cox a household name," +
            " though she struggled to translate that fame into a film career; still, there were successes at the" +
            " movies, including \"Scream\" (1996) and its sequels, as well as \"The Longest Yard\" (2005) and" +
            " \"Bedtime Stories\" (2008). Television, however, remained her strongest palate, and she returned" +
            " there on several occasions; most notably for the sexy drama \"Dirt\" (FX, 2007-08) and \"Cougar" +
            " Town\" (ABC/TBS, 2009-2015), both of which she co-produced with her husband, actor David Arquette." +
            " Throughout her career shifts, she remained an enduring fan favorite, thanks to being immortalized" +
            " on \"Friends,\" but also for her reputation as a performer who consistently sought new challenges" +
            " rather than coasting on past glories.";
    private final String BIO_017 = "From the stand-up stages of Canada to the breakout sketch-comedy hit \"In" +
            " Living Color\" (Fox, 1990-96), award-winning actor Jim Carrey became of Hollywood's most bankable" +
            " comedic stars during the 1990s, thanks to a string of zany blockbusters that began with \"Ace" +
            " Ventura: Pet Detective\" (1994). His Golden Globe-winning performance in the eerily promontory" +
            " \"The Truman Show\" (1998) proved that there was also significant depth and substance beneath his" +
            " hyperkinetic antics, after which point, Carrey divided his efforts between broad comedies such as" +
            " \"Me, Myself and Irene\" (2000) and more esoteric indie dramas such as Milos Forman's \"Man on the" +
            " Moon\" (1999) and Michel Gondry's \"Eternal Sunshine of the Spotless Mind\" (2004). Carrey's unique" +
            " sensibility and characterizations also proved a perfect match for big-screen adaptations of Dr." +
            " Seuss' fanciful classics, \"How the Grinch Stole Christmas\" (2000) and \"Horton Hears a Who!\"" +
            " (2008), and he stayed atop the heap with comedies like \"Bruce Almighty\" (2003). Though his" +
            " box-office clout diminished as he turned to uncharacteristic movies such as \"The Number 23\"" +
            " (2007) and \"I Love You Phillip Morris\" (2009) - as evidenced by the muted response to \"Mr." +
            " Popper's Penguins\" (2011) and \"Dumb & Dumber To\" (2014) - Carrey nonetheless remained one of" +
            " Hollywood's biggest and more beloved stars.";
    private final String BIO_018 = "American actor Jeff Daniels built a versatile career where he moved easily" +
            " between comedy and dramatic roles. Born in Athens, Georgia, he grew up in Chelsea, Michigan. He" +
            " studied theater in college, but left to pursue a professional career in New York. He gained" +
            " notice with his Off-Broadway performance in \"Fifth of July,\" and recreated his role for the" +
            " Broadway production. He began landing small television roles, including a guest appearance on" +
            " \"Hawaii Five-O\" (CBS, 1968-80). The actor moved into film work with a role in Milos Forman's" +
            " \"Ragtime\" (1981). His profile took a huge jump when he starred with Shirley MacLaine, Jack" +
            " Nicholson, and Debra Winger in the Academy Award-winning \"Terms of Endearment\" (1983). He" +
            " was given the chance to step into a starring role when Woody Allen cast him as one of the leads," +
            " along with Mia Farrow, in \"The Purple Rose of Cairo\" (1985). The role would earn Daniels a" +
            " Golden Globe nomination for Best Actor. His role opposite Melanie Griffith in Jonathan Demme's" +
            " \"Something Wild\" (1986) earned him another Golden Globe nom. He began working steadily," +
            " starring in an array of films. One stretch of the 1990s showcased his diversity. He was part of the" +
            " ensemble of the Civil War epic \"Gettysburg\" (1993). He played Keanu Reeves's partner in the" +
            " thriller \"Speed\" (1994), and joined Jim Carrey for the blockbuster sophmoric comedy \"Dumb and" +
            " Dumber\" (1994). Then he starred in the family films \"Fly Away Home\" (1996) and \"101 Dalmations\"" +
            " (1996). The actor never forgot his theater roots, returning to the stage on a regular basis and" +
            " added playwright to his resume as well. He later directed the film version of one of his plays," +
            " \"Escanaba in da Moonlight\" (2001). He earned critical raves for his role as a father struggling" +
            " through a divorce in Noah Baumbach's \"The Squid and the Whale\" (2005). Daniels turned to" +
            " television for what would become one of his most acclaimed roles. The actor starred as TV news anchor" +
            " Will McAvoy in Aaron Sorkin's drama \"The Newsroom\" (HBO, 2012-14). The role earned Daniels an" +
            " Emmy Award. On the big screen, he re-teamed with Carrey for the sequel \"Dumb and Dumber To\" (2014)," +
            " and joined the ensembles of \"The Martian\" (2015) and \"Steve Jobs\" (2015). His strong work on" +
            " television continued with a starring role in the miniseries \"The Looming Tower\" (Hulu, 2018) as" +
            " real-life counter-intelligence agent John O'Neill, who was convinced that the U.S. was being targeted" +
            " by Al-Queda and died in the World Trade Center attacks. He also starred as outlaw Frank Griffin in" +
            " the Western drama \"Godless\" (Netflix, 2018). Daniels earned Emmy nominations for both projects," +
            " winning for \"Godless.\" On stage, he earned critical raves and a Tony nomination for his role as" +
            " Atticus Finch in a 2018 production of \"To Kill a Mockingbird.\"";
    private final String BIO_019 = "Lucy Boynton was a British-American actress who began working at the age of 12," +
            " appeared in a number of indie sleeper hits and TV shows in her adopted homeland of England, before" +
            " breaking out by playing the partner of one of rock n' roll's most beloved singers. Born on January" +
            " 17, 1994 in New York, NY, Boynton was raised in London. Her father, Graham Boynton, was British-born" +
            " and worked as Group Travel Editor of the Telegraph Media Group, while her mother, Adriaane Pielou," +
            " was travel writer. She had an older sister, Emma Louise. Boynton began her career young, making her" +
            " film debut as young Beatrix Potter in the biopic \"Miss Potter\" (2006) when she was just 12 years old." +
            " The next year, Boynton made her TV debut in the BBC film \"Ballet Shoes\" (BBC, 2007), playing a young" +
            " and ambitious ballerina making her way at a prestigious dance school. She would continue to work" +
            " mainly in TV for the next few years, playing Margaret Dashwood in the miniseries of \"Sense and" +
            " Sensibility\" (BBC One, 2008), co-starring as Henrietta Norton, step-daughter of controversial UK" +
            " politician Mo Mowlam (Julie Walters) in the TV biopic \"Mo\" (Channel 4, 2010), and appearing in" +
            " episodes of \"Lewis\" (ITV, 2011-15), \"Borgia\" (Netflix/Canal+, 2011-14), \"Endeavour\"" +
            " (ITV, 2013-), \"Law & Order: UK\" (ITV, 2009-2014), and \"Life in Squares\" (BBC Two, 2015). Boynton" +
            " soon began getting some promising film work, appearing in two critically acclaimed horror films" +
            " by writer/director Osgood Perkins (son of \"Psycho\" [1960] star Anthony Perkins), \"The" +
            " Blackcoat's Daughter\" (2015), and \"I Am the Pretty Thing That Lives in the House\" (2016)," +
            " as well as \"Sing Street\" (2016), a critically acclaimed indie sleeper hit set in 1980s Ireland," +
            " in which she played a mysterious aspiring model who inspires a young man to start a new wave band." +
            " She would next return to TV, co-starring in the short-lived psychological thriller series \"Gypsy\"" +
            " (Netflix, 2017) before playing Countess Helena Andrenyi in Kenneth Branaugh's star-studded take on" +
            " the classic Agatha Christie mystery \"Murder on the Orient Express\" (2017). All this work would" +
            " culminate in 2018, Boynton's breakout year. She appeared in Gareth Evans' cult-themed horror film" +
            " \"Apostle\" (2018), before taking on the role of Mary Austin, the romantic partner turned closest" +
            " confidant of Queen frontman Freddie Mercury (Rami Malek) in the hugely successful biopic \"Bohemian" +
            " Rhapsody\" (2018). ";

    @Override
    public void run(String... args) throws Exception {
    }

    public void createBunchOfPeople() {
        List<Person> people = List.of(
                Person.builder().firstName("Jackie").lastName("Chan")
                        .birthDate(LocalDate.of(1954, 4, 7))
                        .birthPlace("Victoria Peak, Hong Kong")
                        .biography(BIO_000)
                        .build(),
                Person.builder().firstName("Chris").lastName("Tucker")
                        .birthDate(LocalDate.of(1972, 8, 31))
                        .birthPlace("Atlanta, Georgia, USA")
                        .biography(BIO_001)
                        .build(),
                Person.builder().firstName("Anthony").lastName("Quinn")
                        .birthDate(LocalDate.of(1915, 4, 21))
                        .birthPlace("Chihuahua, Mexico")
                        .biography(BIO_002)
                        .build(),
                Person.builder().firstName("Keanu").lastName("Reeves")
                        .birthDate(LocalDate.of(1964, 9, 2))
                        .birthPlace("Beirut, Lebanon")
                        .biography(BIO_003)
                        .build(),
                Person.builder().firstName("Carrie-Anne").lastName("Moss")
                        .birthDate(LocalDate.of(1967, 8, 21))
                        .birthPlace("Vancouver, British Columbia, Canada")
                        .biography(BIO_004)
                        .build(),
                Person.builder().firstName("Laurence").lastName("Fishburne")
                        .birthDate(LocalDate.of(1961, 7, 30))
                        .birthPlace("Augusta, Georgia, USA")
                        .biography(BIO_005)
                        .build(),
                Person.builder().firstName("Arnold").lastName("Schwarzenegger")
                        .birthDate(LocalDate.of(1947, 7, 30))
                        .birthPlace("Thal, Styria, Austria")
                        .biography(BIO_006)
                        .build(),
                Person.builder().firstName("Linda").lastName("Hamilton")
                        .birthDate(LocalDate.of(1956, 9, 26))
                        .birthPlace("Salisbury, Maryland, USA")
                        .biography(BIO_007)
                        .build(),
                Person.builder().firstName("Milla").lastName("Jovovich")
                        .birthDate(LocalDate.of(1975, 12, 17))
                        .birthPlace(" Kiev, Ukrainian SSR, USSR")
                        .biography(BIO_008)
                        .build(),
                Person.builder().firstName("Bruce").lastName("Willis")
                        .birthDate(LocalDate.of(1955, 3, 19))
                        .birthPlace("Idar-Oberstein, West Germany")
                        .biography(BIO_009)
                        .build(),
                Person.builder().firstName("Sylvester").lastName("Stallone")
                        .birthDate(LocalDate.of(1946, 7, 6))
                        .birthPlace("New York, New York, USA")
                        .biography(BIO_010)
                        .build(),
                Person.builder().firstName("Dolph").lastName("Lundgren")
                        .birthDate(LocalDate.of(1957, 11, 3))
                        .birthPlace("Stockholm, Stockholms län, Sweden")
                        .biography(BIO_011)
                        .build(),
                Person.builder().firstName("Jeff").lastName("Bridges")
                        .birthDate(LocalDate.of(1949, 12, 4))
                        .birthPlace("Los Angeles, California, USA")
                        .biography(BIO_012)
                        .build(),
                Person.builder().firstName("Julianne").lastName("Moore")
                        .birthDate(LocalDate.of(1960, 12, 3))
                        .birthPlace("Birthplace: Fayetteville, North Carolina, USA")
                        .biography(BIO_013)
                        .build(),
                Person.builder().firstName("Rami").lastName("Malek")
                        .birthDate(LocalDate.of(1981, 5, 12))
                        .birthPlace("Los Angeles, California, USA")
                        .biography(BIO_014)
                        .build(),
                Person.builder().firstName("Jennifer").lastName("Aniston")
                        .birthDate(LocalDate.of(1969, 2, 11))
                        .birthPlace("Sherman Oaks, California, USA")
                        .biography(BIO_015)
                        .build(),
                Person.builder().firstName("Courteney").lastName("Cox")
                        .birthDate(LocalDate.of(1964, 6, 15))
                        .birthPlace("Birmingham, Alabama, USA")
                        .biography(BIO_016)
                        .build(),
                Person.builder().firstName("Jim").lastName("Carrey")
                        .birthDate(LocalDate.of(1962, 1, 17))
                        .birthPlace("Newmarket, Ontario, Canada")
                        .biography(BIO_017)
                        .build(),
                Person.builder().firstName("Jeff").lastName("Daniels")
                        .birthDate(LocalDate.of(1955, 2, 19))
                        .birthPlace("Athens, Georgia, USA")
                        .biography(BIO_018)
                        .build(),
                Person.builder().firstName("Lucy").lastName("Boynton")
                        .birthDate(LocalDate.of(1994, 1, 17))
                        .birthPlace("New York, New York, USA")
                        .biography(BIO_019)
                        .build()
        );

        logger.info("Created {} products.", personService.createAll(people).size());
    }
}
