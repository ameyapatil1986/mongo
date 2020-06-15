package Random;

import java.util.*;

class Tweet {
    int tweetId;
    int timeStampCounter;

    Tweet(int pTweetId, int pTimeStampCounter) {
        this.tweetId = pTweetId;
        this.timeStampCounter = pTimeStampCounter;
    }
}

public class DesignTwitter {

    Map<Integer, HashSet<Integer>> userFollowerMap;     //user and followees
    Map<Integer, List<Tweet>> tweetMap;  //user and tweets
    int counter;

    /** Initialize your data structure here. */
    public DesignTwitter() {
        userFollowerMap = new HashMap<Integer, HashSet<Integer>>();
        tweetMap = new HashMap<Integer, List<Tweet>>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        tweetMap.putIfAbsent(userId, new ArrayList<Tweet>()).add(new Tweet(tweetId, counter++));
        follow(userId, userId);  //follow self
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Tweet> getNewsFeed(int userId) {
        Set<Integer> allPeopleUserFollows = userFollowerMap.get(userId);

        if (allPeopleUserFollows==null)
            return Collections.emptyList();

        // we want it in descending order.
        final PriorityQueue<Tweet> queue = new PriorityQueue<Tweet>(Comparator.comparing(a -> a.timeStampCounter));

        //get all users' tweets
        for(int uid: allPeopleUserFollows) {
            for (Tweet tweet : tweetMap.get(uid)) {
                queue.add(tweet);
                if (queue.size() > 10) {
                    queue.poll();
                }
            }
        }

        final List<Tweet> result = new ArrayList<Tweet>();
        while(!queue.isEmpty()) {
            Tweet top = queue.poll();
            result.add(top);
        }

        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        userFollowerMap.putIfAbsent(followerId, new HashSet<>()).add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followerId==followeeId)
            return ;

        Set<Integer> set = userFollowerMap.get(followerId);
        if(set==null){
            return;
        }
        set.remove(followeeId);
    }
}
